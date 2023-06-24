package com.enactor.assessment.constant;

public interface ValidationConstant {

	String PARAM_DATE = "date";
	String PARAM_ORIGIN = "origin";
	String PARAM_DESTINATION = "destination";
	String PARAM_PASSENGERS = "passengers";

	String DATE_ONLY_FORMAT = "yyyy-MM-dd";
	String ORIGIN_AND_DESTINATION_REGEX = "[ABCD]";

	String ERROR_INVLID_DATE_FORMAT = "Error: Invalid date. Does not conform to format right format. Example right format '2023-06-24'";
	String ERROR_INVLID_DATE = "Error: Invalid date. Please try with a date after today";
	String ERROR_INVLID_ORIGIN = "Error: Invalid origin. Origin should be one of the following. A, B, C or D";
	String ERROR_INVLID_DESTINATION = "Error: Invalid destination. Destination should be one of the following. A, B, C or D";
	String ERROR_INVLID_PASSENGERS_COUNT_NOT_A_NUMBER = "Error: Invalid passengers count. Not a valid number";
	String ERROR_INVLID_PASSENGERS_COUNT_BELOW_ONE = "Error: Invalid passengers count. Has to be a number more than 0";
}
