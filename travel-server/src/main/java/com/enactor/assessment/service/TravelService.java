package com.enactor.assessment.service;

import com.enactor.assessment.dto.AvailabilityDto;

public interface TravelService {

	AvailabilityDto checkAvailability(String origin, String destination, int passengers);
}