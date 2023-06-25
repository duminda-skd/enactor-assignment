package com.enactor.assessment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import com.enactor.assessment.dto.AvailabilityInboundDto;
import com.enactor.assessment.dto.AvailabilityOutBoundDto;
import com.enactor.assessment.dto.ReservationOutBoundDto;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TravelServiceTest {
	TravelService travelService;
	String dateTomorrow;

	@BeforeAll
	void beforeEach() {
		travelService = new TravelServiceImpl();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
	}
	
	@Test
	@Order(1)
	void checkAvailabilitySuccess() {
		AvailabilityInboundDto availabilityInbound = new AvailabilityInboundDto();
		availabilityInbound.setDate(dateTomorrow);
		availabilityInbound.setOrigin("A");
		availabilityInbound.setDestination("C");
		availabilityInbound.setPassengers(6);
		AvailabilityOutBoundDto availability = travelService.checkAvailability(availabilityInbound);
		assertTrue(availability.isSuccess());
		assertThat(availability.getErrorMessage(), nullValue());
	}
	
	@Test
	@Order(2)
	void checkReservationSuccess() {
		AvailabilityInboundDto availabilityInbound = new AvailabilityInboundDto();
		availabilityInbound.setDate(dateTomorrow);
		availabilityInbound.setOrigin("A");
		availabilityInbound.setDestination("C");
		availabilityInbound.setPassengers(6);
		ReservationOutBoundDto reservation = travelService.reserveSeats(availabilityInbound);
		assertThat(reservation.getErrorMessage(), nullValue());
		assertThat(reservation.getSeatList(), hasSize(not(0)));
	}
	
	@Test
	@Order(3)
	void checkExceedingSeatsReservation() {
		AvailabilityInboundDto availabilityInbound = new AvailabilityInboundDto();
		availabilityInbound.setDate(dateTomorrow);
		availabilityInbound.setOrigin("A");
		availabilityInbound.setDestination("C");
		availabilityInbound.setPassengers(30);
		ReservationOutBoundDto okReservation = travelService.reserveSeats(availabilityInbound);
		availabilityInbound.setPassengers(6);
		ReservationOutBoundDto exceedingSeatsReservation = travelService.reserveSeats(availabilityInbound);
		assertTrue(!exceedingSeatsReservation.isSuccess());
		assertThat(exceedingSeatsReservation.getErrorMessage(), containsString("Seat limit exceeded"));
	}
}
