package com.enactor.assessment.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enactor.assessment.constant.ValidationConstant;
import com.enactor.assessment.dto.InputValidationFailureResult;

public class InputValidator implements ValidationConstant {

	public List<InputValidationFailureResult> validateCheckAvailabilityInput(Map<String, String> availabilityParams) {
		List<InputValidationFailureResult> validationFailures = new ArrayList<InputValidationFailureResult>();
		// check and add to list if not null (not null means validation failure)
		InputValidationFailureResult dateValidationResult = validateDate(PARAM_DATE,
				availabilityParams.get(PARAM_DATE));
		if (dateValidationResult != null) {
			validationFailures.add(dateValidationResult);
		}
		InputValidationFailureResult originValidationResult = validateOrigin(PARAM_ORIGIN,
				availabilityParams.get(PARAM_ORIGIN));
		if (originValidationResult != null) {
			validationFailures.add(originValidationResult);
		}
		InputValidationFailureResult destinationValidationResult = validateDestination(PARAM_DESTINATION,
				availabilityParams.get(PARAM_ORIGIN), availabilityParams.get(PARAM_DESTINATION));
		if (destinationValidationResult != null) {
			validationFailures.add(destinationValidationResult);
		}
		InputValidationFailureResult passengersValidationResult = validatePassengers(PARAM_PASSENGERS,
				availabilityParams.get(PARAM_PASSENGERS));
		if (passengersValidationResult != null) {
			validationFailures.add(passengersValidationResult);
		}
		// send results
		return validationFailures;
	}

	private InputValidationFailureResult validateDate(String paramName, String dateStr) {
		LocalDate date;
		try {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_ONLY_FORMAT);
			date = LocalDate.parse(dateStr, dateFormatter);
		} catch (DateTimeParseException e) {
			// We have a validation failure. Invalid format.
			return new InputValidationFailureResult(paramName, ERROR_INVLID_DATE_FORMAT);
		}
		LocalDate today = LocalDate.now();
		boolean isAfterToday = date.isAfter(today);
		if (!isAfterToday) {
			// We have a validation failure. Cannot book on date before tomorrow.
			return new InputValidationFailureResult(paramName, ERROR_INVLID_DATE);
		}
		// We're good!
		return null;
	}

	private InputValidationFailureResult validateOrigin(String paramName, String origin) {
		if (!origin.matches(ORIGIN_AND_DESTINATION_REGEX)) {
			// We have a validation failure. Wrong origin.
			return new InputValidationFailureResult(paramName, ERROR_INVLID_ORIGIN);
		}
		// We're good!
		return null;
	}

	private InputValidationFailureResult validateDestination(String paramName, String origin, String destination) {
		if (!destination.matches(ORIGIN_AND_DESTINATION_REGEX)) {
			// We have a validation failure. Wrong origin.
			return new InputValidationFailureResult(paramName, ERROR_INVLID_DESTINATION);
		}
		if (origin.equals(destination)) {
			// We have a validation failure. Destination same as origin.
			return new InputValidationFailureResult(paramName, ERROR_SAME_ORIGIN_AND_DESTINATION);
		}
		// We're good!
		return null;
	}
	
	private InputValidationFailureResult validatePassengers(String paramName, String passengersStr) {
		int passengers;
		try {
			passengers = Integer.parseInt(passengersStr);
        } catch (NumberFormatException e) {
        	// We have a validation failure. Passengers count is not an int
            return new InputValidationFailureResult(paramName, ERROR_INVLID_PASSENGERS_COUNT_NOT_A_NUMBER);
        }
		if (passengers < 1) {
			// We have a validation failure. Passengers count cannot be less than 1.
			return new InputValidationFailureResult(paramName, ERROR_INVLID_PASSENGERS_COUNT_BELOW_ONE);
		}
		// We're good!
		return null;
	}
}
