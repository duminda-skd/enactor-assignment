package com.enactor.assessment.service;

import java.util.Map;
import java.util.Scanner;

import com.enactor.assessment.dto.AvailabilityDto;

public interface TravelService {

	AvailabilityDto doAvailabilityCheck(Map<String, String> availabilityParams);

	void doTicketReserve(Map<String, String> availabilityParams);
}
