package com.enactor.assessment.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enactor.assessment.controller.TravelController;
import com.enactor.assessment.dto.AvailabilityOutBoundDto;
import com.enactor.assessment.dto.AvailabilityInboundDto;
import com.enactor.assessment.util.GenericUtil;

public class MyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AvailabilityInboundDto availabilityInbound = new AvailabilityInboundDto();
		availabilityInbound.setDate(request.getParameter("date"));
		availabilityInbound.setOrigin(request.getParameter("origin"));
		availabilityInbound.setDestination(request.getParameter("destination"));
		availabilityInbound.setPassengers(Integer.parseInt(request.getParameter("passengers")));
		
		TravelController travelController = new TravelController();
		String availability = travelController.checkAvailability(availabilityInbound);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(availability);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String payload = request.getReader().lines().collect(Collectors.joining());
		AvailabilityInboundDto availabilityDto = GenericUtil.jsonToObject(payload, AvailabilityInboundDto.class);
		
		TravelController travelController = new TravelController();
		String reservation = travelController.reserveSeats(availabilityDto);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(reservation);
	}
}