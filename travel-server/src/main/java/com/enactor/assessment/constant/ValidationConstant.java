package com.enactor.assessment.constant;

public interface ValidationConstant {

	String PARAM_DATE = "date";
	String PARAM_ORIGIN = "origin";
	String PARAM_DESTINATION = "destination";
	String PARAM_PASSENGERS = "passengers";

	String DATE_ONLY_FORMAT = "yyyy-MM-dd";
	String ORIGIN_AND_DESTINATION_REGEX = "[ABCD]";
	
	String ERROR_VALIDATION_BASE_MESSAGE = "There were errors for the following inputs.";

	String ERROR_INVLID_DATE_FORMAT = "Invalid date. Does not conform to format right format. Example right format '2023-06-24'";
	String ERROR_INVLID_DATE = "Invalid date. Please try with a date after today";
	String ERROR_INVLID_ORIGIN = "Invalid origin. Origin should be one of the following. A, B, C or D";
	String ERROR_INVLID_DESTINATION = "Invalid destination. Destination should be one of the following. A, B, C or D";
	String ERROR_INVLID_PASSENGERS_COUNT_NOT_A_NUMBER = "Invalid passengers count. Not a valid number";
	String ERROR_INVLID_PASSENGERS_COUNT_BELOW_ONE = "Invalid passengers count. Has to be a number more than 0";
}
