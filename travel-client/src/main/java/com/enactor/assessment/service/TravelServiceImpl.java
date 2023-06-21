package com.enactor.assessment.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.enactor.assessment.constant.AvailabilityAndReservationConstants;
import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.util.JsonBodyHandler;

public class TravelServiceImpl implements TravelService, AvailabilityAndReservationConstants {
	
	@Override
	public void doAvailabilityCheck(Map<String, String> availabilityParams) {
		String modifiedUrl = getModifiedUrlWithParams(availabilityParams);
		AvailabilityDto response = null;
		try {
			URI targetURI = new URI(modifiedUrl);
			HttpRequest httpRequest = HttpRequest.newBuilder().uri(targetURI).header("Accept", "application/json").GET()
					.build();
			response = HttpClient.newHttpClient()
					.send(httpRequest, new JsonBodyHandler<AvailabilityDto>(AvailabilityDto.class)).body();
			System.out.println("response: " + response);
		} catch (IOException | InterruptedException | URISyntaxException ex) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong while processing availability request. Please try again later.");
		}
	}

	@Override
	public void doTicketReserve() {
		// TODO Auto-generated method stub

	}

	private String getModifiedUrlWithParams(Map<String, String> availabilityParams) {
		String getParams = availabilityParams.keySet().stream().map(key -> key + '=' + availabilityParams.get(key))
				.collect(Collectors.joining("&"));
		String modifiedUrl = BASE_URL + '?' + getParams;
		return modifiedUrl;
	}


}
