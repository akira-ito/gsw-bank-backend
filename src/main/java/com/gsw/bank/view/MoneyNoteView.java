package com.gsw.bank.view;

import java.math.BigDecimal;

public class MoneyNoteView {
	private BigDecimal value;
	private Integer qtd;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

}
