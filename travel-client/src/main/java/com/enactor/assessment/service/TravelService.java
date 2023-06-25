package com.enactor.assessment.service;

import java.util.Map;
import java.util.Scanner;

import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.dto.ReservationDto;

public interface TravelService {

	AvailabilityDto doAvailabilityCheck(Map<String, String> availabilityParams);

	ReservationDto doTicketReserve(Map<String, String> availabilityParams);
}
