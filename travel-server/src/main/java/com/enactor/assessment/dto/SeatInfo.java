package com.enactor.assessment.dto;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SeatInfo {

	private ReadWriteLock lock;
	private int departureJourneyBookedSeats;
	private int arrivalJourneyBookedSeats;
	private String departureJourneyTime;
	private String arrivalJourneyTime;
	
	public SeatInfo() {
		this.lock = new ReentrantReadWriteLock();
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

	public ReadWriteLock getLock() {
		return lock;
	}
}
