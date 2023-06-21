package com.enactor.assessment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.enactor.assessment.constant.UtilConstants;

public class SeatNumberResolver implements UtilConstants {

	private SeatNumberResolver() {
	}

	private static class SeatNumberResolverHolder {
		private static int departureJourneyTotalSeatsBooked = 0;
		private static int arrivalJourneyTotalSeatsBooked = 0;
	}

	public static List<String> block(String origin, String destination, int bookedSeats) {
		return null;
	}

	private void determineJourneyType(String origin, String destination) {
		
	}

	private static List<String> blockToJourney(int bookedSeats) {
		System.out.println("Current seats booked for departure journey: " + SeatNumberResolverHolder.departureJourneyTotalSeatsBooked);

		List<String> seatsList = new ArrayList();

		IntStream.range(0, bookedSeats).forEach(ix -> {
			int row = (SeatNumberResolverHolder.departureJourneyTotalSeatsBooked / SEAT_LETTER_ORDER.size()) + 1;
			int letterIx = SeatNumberResolverHolder.departureJourneyTotalSeatsBooked % SEAT_LETTER_ORDER.size();
			String letter = SEAT_LETTER_ORDER.get(letterIx);
			String seatNumber = row + letter;
			seatsList.add(seatNumber);
			++SeatNumberResolverHolder.departureJourneyTotalSeatsBooked;
		});

		System.out.println("After reservation seats booked for departure journey: " + SeatNumberResolverHolder.departureJourneyTotalSeatsBooked);
		System.out.println("Seats list: " + seatsList);

		return seatsList;
	}
	
	private static List<String> blockFromJourney(int bookedSeats) {
		System.out.println("Current from journey seats booked for arrival journey: " + SeatNumberResolverHolder.arrivalJourneyTotalSeatsBooked);

		List<String> seatsList = new ArrayList();

		IntStream.range(0, bookedSeats).forEach(ix -> {
			int row = (SeatNumberResolverHolder.arrivalJourneyTotalSeatsBooked / SEAT_LETTER_ORDER.size()) + 1;
			int letterIx = SeatNumberResolverHolder.arrivalJourneyTotalSeatsBooked % SEAT_LETTER_ORDER.size();
			String letter = SEAT_LETTER_ORDER.get(letterIx);
			String seatNumber = row + letter;
			seatsList.add(seatNumber);
			++SeatNumberResolverHolder.arrivalJourneyTotalSeatsBooked;
		});

		System.out.println("After reservation seats booked for arrival journey: " + SeatNumberResolverHolder.arrivalJourneyTotalSeatsBooked);
		System.out.println("Seats list: " + seatsList);

		return seatsList;
	}

}
