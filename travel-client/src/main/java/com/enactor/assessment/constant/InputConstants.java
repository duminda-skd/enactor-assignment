package com.enactor.assessment.constant;

public interface InputConstants {

	String WELCOME_MESSAGE = "Welcome to VTravel Booking. Please enter following details to check availability";
	String PROMPT_DATE = "Enter date (in the format yyyy-MM-dd)";
	String PROMPT_ORIGIN = "Enter origin";
	String PROMPT_DESTINATION = "Enter destination";
	String PROMPT_PASSENGERS = "Enter # of passengers";

	String PARAM_DATE = "date";
	String PARAM_ORIGIN = "origin";
	String PARAM_DESTINATION = "destination";
	String PARAM_PASSENGERS = "passengers";

	String PROMPT_TERMINATION = "Do you wish to entertain the availability service again or do you wish to quit?"
			+ " Enter 'q' to quit any other character for try availability service again";
	String QUIT_FLAG = "q";

	String PROMPT_USER_CONFIRMATION = "Do you wish to continue to reservation with above info?"
			+ " Enter 'y' to confirm any other character for try availability service again";
	String USER_CONFIRMATION_FLAG = "y";
}
