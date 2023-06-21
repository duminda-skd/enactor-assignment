package com.enactor.assessment.dto;

public class AvailabilityDto {

	public String origin;
	public String destination;
	public int passengers;
	public double price;
	
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

	@Override
	public String toString() {
		return "AvailabilityDto [origin=" + origin + ", destination=" + destination + ", passengers=" + passengers
				+ ", price=" + price + "]";
	}
}
