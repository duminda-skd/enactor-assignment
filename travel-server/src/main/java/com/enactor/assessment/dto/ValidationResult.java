package com.enactor.assessment.dto;

public class ValidationResult {

	private boolean validationFailed;
	private String errorMessage;
	
	public ValidationResult(boolean validationFailed, String errorMessage) {
		this.validationFailed = validationFailed;
		this.errorMessage = errorMessage;
	}

	public boolean isValidationFailed() {
		return validationFailed;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return "ValidationResult [validationFailed=" + validationFailed + ", errorMessage=" + errorMessage + "]";
	}
}
