package com.enactor.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.enactor.assessment.constant.JourneyType;
import com.enactor.assessment.constant.TravelConstants;
import com.enactor.assessment.dto.AvailabilityInboundDto;
import com.enactor.assessment.dto.AvailabilityOutBoundDto;
import com.enactor.assessment.dto.ReservationOutBoundDto;
import com.enactor.assessment.dto.SeatInfo;
import com.enactor.assessment.repository.PricingRepo;
import com.enactor.assessment.repository.PricingRepoImpl;
import com.enactor.assessment.repository.SeatManagerRepoImpl;

public class TravelServiceImpl implements TravelService, TravelConstants {
	private PricingRepo pricingRepo = new PricingRepoImpl();

	@Override
	public AvailabilityOutBoundDto checkAvailability(AvailabilityInboundDto availabilityInbound) {
		// get seat info for the date
		SeatInfo seatInfo = SeatManagerRepoImpl.getSeatInfoByDate(availabilityInbound.getDate());
		// get journey type (departure or arrival)
		JourneyType journeyType = determineJourneyType(availabilityInbound.getOrigin(),
				availabilityInbound.getDestination());
		// check if seats are available
		boolean seatLimitExceeding = checkSeatLimitExceeding(journeyType, availabilityInbound.getPassengers(),
				seatInfo);
		// if not available send unsuccessful flag with error message
		if (seatLimitExceeding) {
			return generateAvailabilityUnsuccessfulResponse();
		}
		// if available send success flag with data
		return generateAvailabilitySuccessfulResponse(availabilityInbound);
	}

	private AvailabilityOutBoundDto generateAvailabilitySuccessfulResponse(AvailabilityInboundDto availabilityInbound) {
		AvailabilityOutBoundDto availabilityOutbound = new AvailabilityOutBoundDto();
		availabilityOutbound.setSuccess(true);
		availabilityOutbound.setDate(availabilityInbound.getDate());
		availabilityOutbound.setOrigin(availabilityInbound.getOrigin());
		availabilityOutbound.setDestination(availabilityInbound.getDestination());
		availabilityOutbound.setPassengers(availabilityInbound.getPassengers());
		availabilityOutbound.setPrice(calcPrice(availabilityInbound.getOrigin(), availabilityInbound.getDestination(),
				availabilityInbound.getPassengers()));
		return availabilityOutbound;
	}

	private AvailabilityOutBoundDto generateAvailabilityUnsuccessfulResponse() {
		AvailabilityOutBoundDto availabilityOutbound = new AvailabilityOutBoundDto();
		availabilityOutbound.setSuccess(false);
		availabilityOutbound.setErrorMessage(ERROR_SEAT_LIMIT_EXCEEDED);
		return availabilityOutbound;
	}

	private JourneyType determineJourneyType(String origin, String destination) {
		int originIx = DESTINATION_ORDER.indexOf(origin);
		int destinationIx = DESTINATION_ORDER.indexOf(destination);
		return originIx < destinationIx ? JourneyType.DEPARTURE_JOURNEY : JourneyType.ARRIVAL_JOURNEY;
	}

	private boolean checkSeatLimitExceeding(JourneyType journeyType, int requestedSeats, SeatInfo seatInfo) {
		int currentBookedSeats = journeyType.equals(JourneyType.DEPARTURE_JOURNEY)
				? seatInfo.getDepartureJourneyBookedSeats()
				: seatInfo.getArrivalJourneyBookedSeats();
		return currentBookedSeats + requestedSeats > SEAT_LIMIT;
	}

	private double calcPrice(String origin, String destination, int passengers) {
		double priceForOne = pricingRepo.getPricingByOriginAndDestination(origin, destination);
		return priceForOne * passengers;
	}

	@Override
	public ReservationOutBoundDto reserveSeats(AvailabilityInboundDto availabilityInbound) {
		double price = calcPrice(availabilityInbound.getOrigin(), availabilityInbound.getDestination(),
				availabilityInbound.getPassengers());
		// get seat info for the date
		SeatInfo seatInfo = SeatManagerRepoImpl.getSeatInfoByDate(availabilityInbound.getDate());
		// get journey type (departure or arrival)
		JourneyType journeyType = determineJourneyType(availabilityInbound.getOrigin(),
				availabilityInbound.getDestination());
		List<String> seatList = journeyType.equals(JourneyType.DEPARTURE_JOURNEY)
				? blockDepartureJourney(seatInfo, availabilityInbound.getPassengers())
				: blockArrivalJourney(seatInfo, availabilityInbound.getPassengers());
		return generateReservationResponse(price, availabilityInbound, seatInfo, seatList, journeyType);
	}

	private ReservationOutBoundDto generateReservationResponse(double price, AvailabilityInboundDto availabilityInbound,
			SeatInfo seatInfo, List<String> seatList, JourneyType journeyType) {
		ReservationOutBoundDto reservationOutBound = new ReservationOutBoundDto();
		reservationOutBound.setOrigin(availabilityInbound.getOrigin());
		reservationOutBound.setDestination(availabilityInbound.getDestination());
		reservationOutBound.setPassengers(availabilityInbound.getPassengers());
		reservationOutBound.setPrice(price);
		reservationOutBound.setJourneyDate(availabilityInbound.getDate());
		reservationOutBound.setJourneyStartTime(
				journeyType.equals(JourneyType.DEPARTURE_JOURNEY) ? seatInfo.getDepartureJourneyTime()
						: seatInfo.getArrivalJourneyTime());
		reservationOutBound.setSeatList(seatList);
		return reservationOutBound;
	}

	private List<String> blockDepartureJourney(SeatInfo seatInfo, int requestedSeats) {
		System.out.println("Current seats booked for departure journey: " + seatInfo.getDepartureJourneyBookedSeats());

		List<String> seatsList = new ArrayList();

		IntStream.range(0, requestedSeats).forEach(ix -> {
			int row = (seatInfo.getDepartureJourneyBookedSeats() / SEAT_LETTER_ORDER.size()) + 1;
			int letterIx = seatInfo.getDepartureJourneyBookedSeats() % SEAT_LETTER_ORDER.size();
			String letter = SEAT_LETTER_ORDER.get(letterIx);
			String seatNumber = row + letter;
			seatsList.add(seatNumber);
			seatInfo.setDepartureJourneyBookedSeats(seatInfo.getDepartureJourneyBookedSeats() + 1);
		});

		System.out.println(
				"After reservation seats booked for departure journey: " + seatInfo.getDepartureJourneyBookedSeats());
		System.out.println("Seats list: " + seatsList);

		return seatsList;
	}

	private List<String> blockArrivalJourney(SeatInfo seatInfo, int requestedSeats) {
		System.out.println(
				"Current from journey seats booked for arrival journey: " + seatInfo.getArrivalJourneyBookedSeats());

		List<String> seatsList = new ArrayList();

		IntStream.range(0, requestedSeats).forEach(ix -> {
			int row = (seatInfo.getArrivalJourneyBookedSeats() / SEAT_LETTER_ORDER.size()) + 1;
			int letterIx = seatInfo.getArrivalJourneyBookedSeats() % SEAT_LETTER_ORDER.size();
			String letter = SEAT_LETTER_ORDER.get(letterIx);
			String seatNumber = row + letter;
			seatsList.add(seatNumber);
			seatInfo.setArrivalJourneyBookedSeats(seatInfo.getArrivalJourneyBookedSeats() + 1);
		});

		System.out.println(
				"After reservation seats booked for arrival journey: " + seatInfo.getArrivalJourneyBookedSeats());
		System.out.println("Seats list: " + seatsList);

		return seatsList;
	}

}
