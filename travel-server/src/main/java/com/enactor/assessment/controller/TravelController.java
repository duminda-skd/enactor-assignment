package com.enactor.assessment.controller;

import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;
import com.enactor.assessment.util.GenericUtil;
import com.fasterxml.jackson.core.JsonProcessingException;


public class TravelController {
	TravelService travelService = new TravelServiceImpl();

	public String checkAvailability(String origin, String destination, int passengers) throws JsonProcessingException {
		AvailabilityDto availability = travelService.checkAvailability(origin, destination, passengers);
		String availabilityResponse = GenericUtil.serialize(availability);
		return availabilityResponse;
	}
}
