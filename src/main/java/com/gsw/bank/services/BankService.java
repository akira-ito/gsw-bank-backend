package com.gsw.bank.services;

import java.math.BigDecimal;

import com.gsw.bank.view.MoneyNotesView;

public interface BankService {
	public MoneyNotesView saque(String userId, BigDecimal value);
}
