package com.gsw.bank.view;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class SaqueBodyView {
	
	@NotNull
	private BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
