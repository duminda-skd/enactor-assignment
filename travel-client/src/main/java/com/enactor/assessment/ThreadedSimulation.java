package com.enactor.assessment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import com.enactor.assessment.constant.MainFlowConstant;
import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.dto.InputValidationFailureResult;
import com.enactor.assessment.dto.ReservationDto;
import com.enactor.assessment.service.InputService;
import com.enactor.assessment.service.InputServiceImpl;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;
import com.enactor.assessment.validator.InputValidator;

public class ThreadedSimulation implements Runnable, MainFlowConstant {
	private CountDownLatch latch;
	private Map<String, String> availabilityParams;
	
	public ThreadedSimulation() { }

	public ThreadedSimulation(CountDownLatch latch, Map<String, String> availabilityParams) {
		this.latch = latch;
		this.availabilityParams = availabilityParams;
	}

	@Override
	public void run() {
		try {
			latch.await();
			// System.out.printf("[ %s ] starts at: %s\n", Thread.currentThread().getName(),
			// Instant.now());
			doAvailabilityCheckAndReservation(availabilityParams);
		} catch (InterruptedException ex) {
			// ex.printStackTrace();
		}
	}

	public void runSimulation(int numberOfUsers) {
		// working on getting availability input
		InputService inputService = new InputServiceImpl();
		Map<String, String> availabilityParams = inputService.getAvailabilityInput();
		// do validation
		doValidation(availabilityParams);
		// running threads for the number of simulated users
		CountDownLatch latch = new CountDownLatch(1);
		IntStream.range(0, numberOfUsers).forEach(num -> {
			Thread myThread = new Thread(new ThreadedSimulation(latch, availabilityParams), "simulated-client-" + num);
			myThread.start();
		});
		latch.countDown();
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

	private void doAvailabilityCheckAndReservation(Map<String, String> availabilityParams) {
		TravelService travelService = new TravelServiceImpl();
		AvailabilityDto availabilityCheck = travelService.doAvailabilityCheck(availabilityParams);
		if (availabilityCheck.isSuccess()) {
			System.out.println(MESSAGE_AVAILABILITY_CHECK_SUCCESS);
			ReservationDto reservation = travelService.doTicketReserve(availabilityParams);
			printReservationData(reservation);
		} else {
			System.out.println(MESSAGE_FAILED_DUE_TO_REASONS + availabilityCheck.getErrorMessage());
			System.out.println(MESSAGE_PLEASE_TRY_AGAIN);
		}
	}

	private void printReservationData(ReservationDto reservation) {
		if (reservation.isSuccess()) {
			System.out.println("--------------------------------------------------------");
			System.out.println(Thread.currentThread().getName() + ":" + MESSAGE_RESERVATION_SUCCESS);
			System.out.println(Thread.currentThread().getName() + ":" + RESERVATION_REFERENCE + reservation.getBookingReferece());
			System.out.println(Thread.currentThread().getName() + ":" + RESERVATION_ORIGIN + reservation.getOrigin());
			System.out.println(Thread.currentThread().getName() + ":" + RESERVATION_DESTINATION + reservation.getDestination());
			System.out.println(Thread.currentThread().getName() + ":" + RESERVATION_JOURNEY_DATE + reservation.getJourneyDate());
			System.out.println(Thread.currentThread().getName() + ":" + RESERVATION_JOURNEY_TIME + reservation.getJourneyStartTime());
			System.out.println(Thread.currentThread().getName() + ":" + RESERVATION_PRICE + reservation.getPrice());
			System.out.println(Thread.currentThread().getName() + ":" + RESERVATION_SEAT_LIST + reservation.getSeatList());
			System.out.println("--------------------------------------------------------");
		} else {
			System.out.println("--------------------------------------------------------");
			System.out.println(Thread.currentThread().getName() + ":" + MESSAGE_RESERVATION_ERROR);
			System.out.println("--------------------------------------------------------");
		}
	}
}
