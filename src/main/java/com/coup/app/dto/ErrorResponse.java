package com.coup.app.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for the error response.
 */
@SuppressWarnings("unused")
public class ErrorResponse {
	private Set<String> errors = new HashSet<>();

	public Set<String> getErrors() {
		return errors;
	}

	public void addError(String error) {
		this.errors.add(error);
	}
}
