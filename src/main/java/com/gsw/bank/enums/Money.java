package com.gsw.bank.enums;

import java.math.BigDecimal;

public enum Money {
	HUNDRED(BigDecimal.valueOf(100)), FIFTY(BigDecimal.valueOf(50)), TWENTY(BigDecimal.valueOf(20)), TEN(
			BigDecimal.valueOf(10));

	private BigDecimal value;

	private Money(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValue() {
		return value;
	}

	public boolean canDiscount(BigDecimal val) {
		return this.value.compareTo(val) <= 0;
	}

	public BigDecimal[] discount(BigDecimal val) {
		BigDecimal[] divideAndRemainder = val.divideAndRemainder(this.value);
		return divideAndRemainder;
	}
	
	
}
