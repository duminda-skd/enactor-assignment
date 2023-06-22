package com.enactor.assessment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericUtil<T> {

	public static <T> String objectToJson(T t) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(t);
	}
	
	public static <T> T jsonToObject(String jsonStr, Class<T> toObjectOfThis) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonStr, toObjectOfThis);
	}
}
