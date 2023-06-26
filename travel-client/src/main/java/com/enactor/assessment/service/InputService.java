package com.enactor.assessment.service;

import java.util.Map;
import java.util.Scanner;

import com.enactor.assessment.dto.RunSimulationResponse;

public interface InputService {
	
	RunSimulationResponse evalRunSimulation();
	
	Map<String, String> getAvailabilityInput();

	boolean getUserConfirmationForReservation();

}
