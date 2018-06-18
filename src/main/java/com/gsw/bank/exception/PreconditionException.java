package com.gsw.bank.exception;

public class PreconditionException extends RuntimeException{
	private static final long serialVersionUID = -1063295786319323821L;
	private ApiError error;

	public PreconditionException(ApiError err) {
		this.error = err;
	}
	
	public ApiError getError() {
		return error;
	}
}
