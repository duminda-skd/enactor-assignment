package com.enactor.assessment.service;

import java.util.Map;
import java.util.Scanner;

public interface TravelService {

	void doAvailabilityCheck(Map<String, String> availabilityParams);

	void doTicketReserve(Map<String, String> availabilityParams);
}
