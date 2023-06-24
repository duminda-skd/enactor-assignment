package com.enactor.assessment.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.enactor.assessment.constant.InputConstants;

public class InputServiceImpl implements InputService, InputConstants {

	@Override
	public Map<String, String> getAvailabilityInput(Scanner myScanner) {
		System.out.println(WELCOME_MESSAGE);
		
		// getting input from user
		System.out.println(PROMPT_DATE);
		String date = myScanner.nextLine();
		System.out.println(PROMPT_ORIGIN);
		String origin = myScanner.nextLine();
		System.out.println(PROMPT_DESTINATION);		
		String destination = myScanner.nextLine();
		System.out.println(PROMPT_PASSENGERS);
		String passengers = myScanner.nextLine();

		// Form a map of the input taken from user so that it can be processed as needed 
		Map<String, String> availabilityParams = new HashMap();
		availabilityParams.put(PARAM_DATE, date);
		availabilityParams.put(PARAM_ORIGIN, origin);
		availabilityParams.put(PARAM_DESTINATION, destination);
		availabilityParams.put(PARAM_PASSENGERS, passengers);

		return availabilityParams;
	}
	
	@Override
	public boolean getUserConfirmationForReservation(Scanner myScanner) {
		System.out.println(PROMPT_USER_CONFIRMATION);
		String wantToReserve = myScanner.nextLine();
		return wantToReserve.toLowerCase().equals(USER_CONFIRMATION_FLAG);
	}

	@Override
	public boolean evalTermination(Scanner myScanner) {
		System.out.println(PROMPT_TERMINATION);
		String wantToQuit = myScanner.nextLine();
		// if user press 'q' then send true
		return wantToQuit.toLowerCase().equals(QUIT_FLAG);
	}
}
