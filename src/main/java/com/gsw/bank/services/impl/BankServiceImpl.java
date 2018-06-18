package com.gsw.bank.services.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gsw.bank.enums.Money;
import com.gsw.bank.exception.ApiError;
import com.gsw.bank.exception.PreconditionException;
import com.gsw.bank.model.User;
import com.gsw.bank.repository.UserRepository;
import com.gsw.bank.services.BankService;
import com.gsw.bank.services.MessageService;
import com.gsw.bank.view.MoneyNotesView;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageService messageService;

	@Override
	public MoneyNotesView saque(String userId, BigDecimal value) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new PreconditionException(ApiError.builder().code("1").message(messageService.getMessage("user.notFound")).build());
		if (ObjectUtils.isEmpty(value))
			throw new PreconditionException(ApiError.builder().code("2").message("Valor do saque não pode ser nulo").build());

		MoneyNotesView moneyNotes = getMoneyNotes(value);
		return moneyNotes;
	}

	private MoneyNotesView getMoneyNotes(final BigDecimal val) {
		BigDecimal value = new BigDecimal(val.toString());
		List<Money> values = Arrays.asList(Money.values());
		MoneyNotesView moneyNotes = new MoneyNotesView();

		for (Money money : values) {
			if (money.canDiscount(value)) {
				BigDecimal[] result = money.discount(value);
				moneyNotes.addMoneyNotes(money, result[0].intValue());
				value = result[1];
			}
			
			if (BigDecimal.ZERO.compareTo(value) >= 0)
				break;
		}
		
		moneyNotes.setMoneyRest(value);
		return moneyNotes;
	}

}
