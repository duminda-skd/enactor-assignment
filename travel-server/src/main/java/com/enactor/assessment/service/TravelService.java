package com.enactor.assessment.service;

import com.enactor.assessment.dto.AvailabilityOutBoundDto;
import com.enactor.assessment.dto.ReservationOutBoundDto;
import com.enactor.assessment.dto.AvailabilityInboundDto;

public interface TravelService {

	AvailabilityOutBoundDto checkAvailability(AvailabilityInboundDto availabilityInbound);

	ReservationOutBoundDto reserveSeats(AvailabilityInboundDto availabilityInbound);
}