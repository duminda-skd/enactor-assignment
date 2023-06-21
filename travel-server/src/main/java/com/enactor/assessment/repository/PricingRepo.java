package com.enactor.assessment.repository;

public interface PricingRepo {

	double getPricingByOriginAndDestination(String origin, String destination);
}
