package com.gsw.bank.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gsw.bank.exception.ApiError;
import com.gsw.bank.exception.PreconditionException;

@ControllerAdvice
public class BankResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		Map<String, String> errors = new HashMap<>();
		fieldErrors.forEach(field -> {
			errors.put(field.getField(), field.getDefaultMessage());
		});

		ApiError errorDetails = ApiError.builder().code("500").message(errors).build();
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String unsupported = "Unsupported content type: " + ex.getContentType();
		String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());
		ApiError errorMessage = ApiError.builder().code("415").message(unsupported + " " + supported).build();
		return new ResponseEntity<Object>(errorMessage, headers, status);
	}

	// @Override
	// protected ResponseEntity<Object> handleNoHandlerFoundException(
	// NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
	// WebRequest request) {
	// String error = "No handler found for " + ex.getHttpMethod() + " " +
	// ex.getRequestURL();
	//
	// ApiError apiError = new ApiError("404", error);
	// return new ResponseEntity<Object>(apiError, new HttpHeaders(),
	// HttpStatus.NOT_FOUND);
	// }

	@ExceptionHandler(PreconditionException.class)
	public final ResponseEntity<ApiError> handleUserNotFoundException(PreconditionException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getError(), HttpStatus.PRECONDITION_FAILED);
	}

}
