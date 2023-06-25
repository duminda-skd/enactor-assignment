package com.enactor.assessment;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.enactor.assessment.constant.MainFlowConstant;
import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.dto.InputValidationFailureResult;
import com.enactor.assessment.dto.ReservationDto;
import com.enactor.assessment.service.InputService;
import com.enactor.assessment.service.InputServiceImpl;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;
import com.enactor.assessment.validator.InputValidator;

public class MainFlow implements MainFlowConstant {

	public void start() {
		Scanner myScanner = new Scanner(System.in);
		// working on getting availability input
		InputService inputService = new InputServiceImpl();
		Map<String, String> availabilityParams = inputService.getAvailabilityInput(myScanner);
		// do validation
		doValidation(availabilityParams);
		// working on checking availability
		doAvailabilityCheckAndReservation(myScanner, inputService, availabilityParams);
	}

	private void doValidation(Map<String, String> availabilityParams) {
		InputValidator inputValidator = new InputValidator();
		List<InputValidationFailureResult> validationResults = inputValidator
				.validateCheckAvailabilityInput(availabilityParams);
		if (validationResults.size() > 0) {
			validationResults.forEach(result -> System.out.println(result.getErrorMessage()));
			System.out.println(MESSAGE_TRY_AGAIN_AFTER_VALIDATION_MESSAGE);
			System.exit(0);
		}
	}

	private void doAvailabilityCheckAndReservation(Scanner myScanner, InputService inputService,
			Map<String, String> availabilityParams) {
		TravelService travelService = new TravelServiceImpl();
		AvailabilityDto availabilityCheck = travelService.doAvailabilityCheck(availabilityParams);
		if (availabilityCheck.isSuccess()) {
			System.out.println(MESSAGE_AVAILABILITY_CHECK_SUCCESS);
			// working on reservation
			boolean userConfirmed = inputService.getUserConfirmationForReservation(myScanner);
			if (userConfirmed) {
				ReservationDto reservation = travelService.doTicketReserve(availabilityParams);
				printReservationData(reservation);
			}
		} else {
			System.out.println(MESSAGE_FAILED_DUE_TO_REASONS + availabilityCheck.getErrorMessage());
			System.out.println(MESSAGE_PLEASE_TRY_AGAIN);
		}
	}

	private void printReservationData(ReservationDto reservation) {
		if (reservation.isSuccess()) {
			System.out.println(MESSAGE_RESERVATION_SUCCESS);
			System.out.println(RESERVATION_REFERENCE + reservation.getBookingReferece());
			System.out.println(RESERVATION_ORIGIN + reservation.getOrigin());
			System.out.println(RESERVATION_DESTINATION + reservation.getDestination());
			System.out.println(RESERVATION_JOURNEY_DATE + reservation.getJourneyDate());
			System.out.println(RESERVATION_JOURNEY_TIME + reservation.getJourneyStartTime());
			System.out.println(RESERVATION_PRICE + reservation.getPrice());
			System.out.println(RESERVATION_SEAT_LIST + reservation.getSeatList());
		} else {
			System.out.println(MESSAGE_RESERVATION_ERROR);
		}
	}
}
