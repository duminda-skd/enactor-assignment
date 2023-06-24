package com.enactor.assessment.dto;

public class InputValidationFailureResult {

	private String inputName;
	private String errorMessage;
	
	public InputValidationFailureResult(String inputName, String errorMessage) {
		super();
		this.inputName = inputName;
		this.errorMessage = errorMessage;
	}

	public String getInputName() {
		return inputName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return "InputValidationFailureResult [inputName=" + inputName + ", errorMessage=" + errorMessage + "]";
	}
}
