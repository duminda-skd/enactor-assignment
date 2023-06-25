package com.enactor.assessment.dto;

import java.util.List;

public class ReservationDto {
	private boolean success;
	private String errorMessage;
	private String origin;
	private String destination;
	private int passengers;
	private double price;
	private String journeyDate;
	private String journeyStartTime;
	private List<String> seatList;
	private String bookingReferece;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getJourneyStartTime() {
		return journeyStartTime;
	}

	public void setJourneyStartTime(String journeyStartTime) {
		this.journeyStartTime = journeyStartTime;
	}

	public List<String> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<String> seatList) {
		this.seatList = seatList;
	}

	public String getBookingReferece() {
		return bookingReferece;
	}

	public void setBookingReferece(String bookingReferece) {
		this.bookingReferece = bookingReferece;
	}

	@Override
	public String toString() {
		return "ReservationDto [success=" + success + ", errorMessage=" + errorMessage + ", origin=" + origin
				+ ", destination=" + destination + ", passengers=" + passengers + ", price=" + price + ", journeyDate="
				+ journeyDate + ", journeyStartTime=" + journeyStartTime + ", seatList=" + seatList
				+ ", bookingReferece=" + bookingReferece + "]";
	}
}
