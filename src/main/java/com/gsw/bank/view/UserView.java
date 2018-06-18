package com.gsw.bank.view;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserView {

	@NotBlank
	private String name;
	@Email
	private String email;
	@Min(3)
	private String password;
	@NotNull
	private BigDecimal saldo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
