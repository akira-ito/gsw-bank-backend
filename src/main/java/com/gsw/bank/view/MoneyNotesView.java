package com.gsw.bank.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.gsw.bank.enums.Money;

public class MoneyNotesView {

	private LocalDateTime date;
	private BigDecimal moneyRest;
	private LinkedList<MoneyNoteView> moneyNotes;

	public MoneyNotesView() {
		date = LocalDateTime.now();
		moneyNotes = new LinkedList<>();
	}

	public List<MoneyNoteView> getMoneyNotes() {
		return Collections.unmodifiableList(moneyNotes);
	}

	public void addMoneyNotes(Money money, Integer qtd) {
		MoneyNoteView note = new MoneyNoteView();
		note.setValue(money.getValue());
		note.setQtd(qtd);
		moneyNotes.add(note);
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getMoneyRest() {
		return moneyRest;
	}

	public void setMoneyRest(BigDecimal moneyRest) {
		this.moneyRest = moneyRest;
	}
	
	

}
