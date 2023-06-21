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
		System.out.println(QUESTION_ORIGIN);
		String origin = myScanner.nextLine();
		System.out.println(QUESTION_DESTINATION);		
		String destination = myScanner.nextLine();
		System.out.println(QUESTION_PASSENGERS);
		String passengers = myScanner.nextLine();
		
		// Form a map of the input taken from user so that it can be processed as needed 
		Map<String, String> availabilityParams = new HashMap();
		availabilityParams.put(ORIGIN_PARAM, origin);
		availabilityParams.put(DESTINATION_PARAM, destination);
		availabilityParams.put(PASSENGERS_PARAM, passengers);

		return availabilityParams;
	}

	@Override
	public boolean evalTermination(Scanner myScanner) {
		System.out.println(TERMINATION_PROMPT);
		String wantToQuit = myScanner.nextLine();
		// if user press 'q' then send true
		return wantToQuit.toLowerCase().equals(QUIT_FLAG);
	}
}
