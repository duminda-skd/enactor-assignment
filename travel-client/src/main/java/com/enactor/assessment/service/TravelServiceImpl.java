package com.enactor.assessment.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Map;
import java.util.stream.Collectors;

import com.enactor.assessment.constant.AvailabilityAndReservationConstants;
import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.dto.ReservationDto;
import com.enactor.assessment.util.JsonBodyHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TravelServiceImpl implements TravelService, AvailabilityAndReservationConstants {

	@Override
	public AvailabilityDto doAvailabilityCheck(Map<String, String> availabilityParams) {
		String urlWithParams = getModifiedUrlWithParams(availabilityParams);
		AvailabilityDto response = null;
		try {
			URI targetURI = new URI(urlWithParams);
			HttpRequest httpRequest = HttpRequest.newBuilder()
					.uri(targetURI)
					.header("Accept", "application/json")
					.GET()
					.build();
			response = HttpClient.newHttpClient()
					.send(httpRequest, new JsonBodyHandler<AvailabilityDto>(AvailabilityDto.class))
					.body();
			return response;
		} catch (IOException | InterruptedException | URISyntaxException ex) {
			 ex.printStackTrace();
			System.out.println(ERROR_PROCESSING_REQUEST);
			return getErrorneousAvailabilityResponse();
		}
	}

	

	private String getModifiedUrlWithParams(Map<String, String> availabilityParams) {
		String getParams = availabilityParams.keySet().stream().map(key -> key + '=' + availabilityParams.get(key))
				.collect(Collectors.joining("&"));
		String urlWithParams = BASE_URL + '?' + getParams;
		return urlWithParams;
	}

	@Override
	public ReservationDto doTicketReserve(Map<String, String> availabilityParams) {
		String payloadJson = getPostRequestPayloadData(availabilityParams);
		if (payloadJson == null) {
			System.out.println(ERROR_PROCESSING_REQUEST);
			return getErrorneousReservationResponse();
		}
		
		try {
			URI targetURI = new URI(BASE_URL);
			HttpRequest httpRequest = HttpRequest.newBuilder(targetURI)
					.header("Content-Type", "application/json")
					.POST(BodyPublishers.ofString(payloadJson))
					.build();
			ReservationDto response = HttpClient.newHttpClient()
					.send(httpRequest, new JsonBodyHandler<ReservationDto>(ReservationDto.class))
					.body();
			return response;
		} catch (IOException | InterruptedException | URISyntaxException ex) {
			ex.printStackTrace();
			System.out.println(ERROR_PROCESSING_REQUEST);
			return getErrorneousReservationResponse();
		}
	}
	
	private String getPostRequestPayloadData(Map<String, String> availabilityParams) {
		String payloadJson = null;
		try {
			payloadJson = new ObjectMapper().writeValueAsString(availabilityParams);
		} catch (JsonProcessingException ex) {
			// ex.printStackTrace();
		}
		return payloadJson;
	}
	
	private AvailabilityDto getErrorneousAvailabilityResponse() {
		AvailabilityDto response = new AvailabilityDto();
		response.setSuccess(false);
		response.setErrorMessage(INTERNAL_SERVER_ERROR);
		return null;
	}

	private ReservationDto getErrorneousReservationResponse() {
		ReservationDto response = new ReservationDto();
		response.setSuccess(false);
		response.setErrorMessage(INTERNAL_SERVER_ERROR);
		return response;
	}
}