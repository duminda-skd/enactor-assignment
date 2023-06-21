package com.enactor.assessment.service;

import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.repository.PricingRepo;
import com.enactor.assessment.repository.PricingRepoImpl;
import com.enactor.assessment.util.SeatNumberResolver;

public class TravelServiceImpl implements TravelService {
	private PricingRepo pricingRepo = new PricingRepoImpl();

	@Override
	public AvailabilityDto checkAvailability(String origin, String destination, int passengers) {
		AvailabilityDto availabilityDto = new AvailabilityDto();
		
		availabilityDto.setOrigin(origin);
		availabilityDto.setDestination(destination);
		availabilityDto.setPassengers(passengers);
		availabilityDto.setPrice(calcPrice(origin, destination, passengers));
		
		SeatNumberResolver.block(origin, destination, passengers);
		
		return availabilityDto;
	}
	
	private double calcPrice(String origin, String destination, int passengers) {
		double priceForOne = pricingRepo.getPricingByOriginAndDestination(origin, destination);
		return priceForOne * passengers;
	}
}
