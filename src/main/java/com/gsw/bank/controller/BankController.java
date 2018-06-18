package com.gsw.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gsw.bank.services.BankService;
import com.gsw.bank.view.MoneyNotesView;
import com.gsw.bank.view.SaqueBodyView;

@Controller
public class BankController extends BaseController {
	
	@Autowired
	private BankService bankService;

	@RequestMapping(method = RequestMethod.POST, value = "/bank/{userId}/saque", consumes = "application/json", produces = "application/json")
	public ResponseEntity<MoneyNotesView> saque(@PathVariable("userId") String userId, @Valid @RequestBody SaqueBodyView saque) {
		MoneyNotesView moneyNotes = bankService.saque(userId, saque.getValue());
		if (ObjectUtils.isEmpty(moneyNotes.getMoneyNotes())) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().body(moneyNotes);
	}

}
