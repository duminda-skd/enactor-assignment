package com.enactor.assessment.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enactor.assessment.controller.TravelController;

public class MyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		int passengers = Integer.parseInt(request.getParameter("passengers"));
		
		TravelController travelController = new TravelController();
		String availability = travelController.checkAvailability(origin, destination, passengers);
		
		PrintWriter out = response.getWriter();
		out.println(availability);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}