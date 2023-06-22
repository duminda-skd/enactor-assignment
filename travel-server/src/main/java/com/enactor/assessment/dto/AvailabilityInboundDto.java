package com.enactor.assessment.dto;

public class AvailabilityInboundDto {

	private String date;
	private String origin;
	private String destination;
	private int passengers;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "AvailabilityInboundDto [date=" + date + ", origin=" + origin + ", destination=" + destination
				+ ", passengers=" + passengers + "]";
	}
}
