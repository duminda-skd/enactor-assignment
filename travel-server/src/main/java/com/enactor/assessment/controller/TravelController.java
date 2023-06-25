package com.enactor.assessment.controller;

import com.enactor.assessment.dto.AvailabilityOutBoundDto;
import com.enactor.assessment.dto.ReservationOutBoundDto;
import com.enactor.assessment.dto.ValidationResult;
import com.enactor.assessment.dto.AvailabilityInboundDto;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;
import com.enactor.assessment.util.GenericUtil;
import com.enactor.assessment.validator.InputValidator;
import com.fasterxml.jackson.core.JsonProcessingException;


public class TravelController {
	TravelService travelService = new TravelServiceImpl();
	InputValidator inputValidator = new InputValidator();

	public String checkAvailability(AvailabilityInboundDto availabilityInbound) throws JsonProcessingException {
		ValidationResult validationResult = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
		AvailabilityOutBoundDto availability;
		if (validationResult.isValidationFailed()) {
			availability = new AvailabilityOutBoundDto();
			availability.setSuccess(false);
			availability.setErrorMessage(validationResult.getErrorMessage());
		} else {
			availability = travelService.checkAvailability(availabilityInbound);
		}
		String availabilityResponse = GenericUtil.objectToJson(availability);
		return availabilityResponse;
	}
	
	public String reserveSeats(AvailabilityInboundDto availabilityInbound) throws JsonProcessingException {
		ReservationOutBoundDto reservation = travelService.reserveSeats(availabilityInbound);
		String reservationResponse = GenericUtil.objectToJson(reservation);
		return reservationResponse;
	}
}
