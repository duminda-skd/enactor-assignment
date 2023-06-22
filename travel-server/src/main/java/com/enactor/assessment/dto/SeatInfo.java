package com.enactor.assessment.dto;

public class SeatInfo {

	private int departureJourneyBookedSeats;
	private int arrivalJourneyBookedSeats;
	private String departureJourneyTime;
	private String arrivalJourneyTime;
	
	public SeatInfo() {
		this.departureJourneyTime = "05:00";
		this.arrivalJourneyTime = "17:00";
	}

	public int getDepartureJourneyBookedSeats() {
		return departureJourneyBookedSeats;
	}

	public void setDepartureJourneyBookedSeats(int departureJourneyBookedSeats) {
		this.departureJourneyBookedSeats = departureJourneyBookedSeats;
	}

	public int getArrivalJourneyBookedSeats() {
		return arrivalJourneyBookedSeats;
	}

	public void setArrivalJourneyBookedSeats(int arrivalJourneyBookedSeats) {
		this.arrivalJourneyBookedSeats = arrivalJourneyBookedSeats;
	}

	public String getDepartureJourneyTime() {
		return departureJourneyTime;
	}

	public String getArrivalJourneyTime() {
		return arrivalJourneyTime;
	}
}
