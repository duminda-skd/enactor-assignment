package com.enactor.assessment.repository;

import java.util.Map;
import java.util.Set;

import com.enactor.assessment.util.PricingMapGenerator;

public class PricingRepoImpl implements PricingRepo {

	@Override
	public double getPricingByOriginAndDestination(String origin, String destination)  {
		Map<Set<String>, Double> pricingMap = PricingMapGenerator.getPricingMap();
		// create key to get pricing
		Set<String> key = Set.of(origin, destination);
		// get price by generated key
		Double price = pricingMap.get(key);
		return price;
	}
}
