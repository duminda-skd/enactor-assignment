package com.enactor.assessment.constant;

import java.util.List;

public interface TravelConstants {

	List<String> SEAT_LETTER_ORDER = List.of("A", "B", "C", "D");
	
	List<String> DESTINATION_ORDER = List.of("A", "B", "C", "D");
	
	int SEAT_LIMIT = 40;
	
	String ERROR_SEAT_LIMIT_EXCEEDED = "Seat limit exceeded";
}
