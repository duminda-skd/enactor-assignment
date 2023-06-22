package com.enactor.assessment.controller;

import com.enactor.assessment.dto.AvailabilityOutBoundDto;
import com.enactor.assessment.dto.ReservationOutBoundDto;
import com.enactor.assessment.dto.AvailabilityInboundDto;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;
import com.enactor.assessment.util.GenericUtil;
import com.fasterxml.jackson.core.JsonProcessingException;


public class TravelController {
	TravelService travelService = new TravelServiceImpl();

	public String checkAvailability(AvailabilityInboundDto availabilityInbound) throws JsonProcessingException {
		AvailabilityOutBoundDto availability = travelService.checkAvailability(availabilityInbound);
		String availabilityResponse = GenericUtil.objectToJson(availability);
		return availabilityResponse;
	}
	
	public String reserveSeats(AvailabilityInboundDto availabilityInbound) throws JsonProcessingException {
		ReservationOutBoundDto reservation = travelService.reserveSeats(availabilityInbound);
		String reservationResponse = GenericUtil.objectToJson(reservation);
		return reservationResponse;
	}
}
