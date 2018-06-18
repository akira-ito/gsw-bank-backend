package com.gsw.bank.exception;

import lombok.Builder;

@Builder
public class ApiError {
	private String code;
	private Object message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
	public void setCodeAndMessage(String codeMessage) {
		
	}
	
	

}