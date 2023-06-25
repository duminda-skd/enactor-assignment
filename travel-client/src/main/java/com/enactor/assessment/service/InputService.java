package com.enactor.assessment.service;

import java.util.Map;
import java.util.Scanner;

public interface InputService {
	
	Map<String, String> getAvailabilityInput();

	boolean getUserConfirmationForReservation();
	
	boolean evalTermination();
}
