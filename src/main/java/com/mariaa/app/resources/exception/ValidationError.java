package com.mariaa.app.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	// Adiciona um erro por vez na lista
	public void AddError(String field, String message) {
		errors.add(new FieldMessage(field, message));
	}
}
