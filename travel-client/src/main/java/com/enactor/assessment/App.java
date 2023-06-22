package com.enactor.assessment;

import java.util.Map;
import java.util.Scanner;

import com.enactor.assessment.service.InputService;
import com.enactor.assessment.service.InputServiceImpl;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;

public class App {

	public static void main(String[] args) {
		// defining scanner once and for all
		Scanner myScanner = new Scanner(System.in);
		boolean terminate = false;
		
		while (!terminate) {
			// working on getting availability input
			InputService inputService = new InputServiceImpl();
			Map<String, String> availabilityParams = inputService.getAvailabilityInput(myScanner);
			
			TravelService travelService = new TravelServiceImpl();

			// working on checking availability
			travelService.doAvailabilityCheck(availabilityParams);
			// working on reservation
			boolean userConfirmed = inputService.getUserConfirmationForReservation(myScanner);
			travelService.doTicketReserve(availabilityParams);
			
			// working on getting availability input
			terminate = inputService.evalTermination(myScanner);	
		}
	}
}
