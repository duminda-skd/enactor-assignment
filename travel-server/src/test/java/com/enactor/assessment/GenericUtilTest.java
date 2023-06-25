package com.enactor.assessment;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.enactor.assessment.dto.AvailabilityInboundDto;
import com.enactor.assessment.util.GenericUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GenericUtilTest {

	@Nested
	@DisplayName("Tests for the converted object")
	@TestInstance(Lifecycle.PER_CLASS)
	class JsonToObjectTest {
		AvailabilityInboundDto obj;
		String converated;

		@BeforeAll
		void beforeAll() throws JsonProcessingException {
			obj = new AvailabilityInboundDto();
			obj.setOrigin("A");
			obj.setDestination("D");
			converated = GenericUtil.objectToJson(obj);
		}
		
		@Test
		void checkJsonFields() throws Exception {
			assertThat(converated, containsString("origin"));
			assertThat(converated, containsString("destination"));
		}
		
		@Test
		void checkJsonValues() throws Exception {
			assertThat(converated, containsString("A"));
			assertThat(converated, containsString("D"));
		}
	}

	@Nested
	@DisplayName("Tests for the converted object")
	@TestInstance(Lifecycle.PER_CLASS)
	class ObjectToJsonTest {
		String json;
		AvailabilityInboundDto obj;

		@BeforeAll
		void beforeAll() throws JsonProcessingException {
			json = "{\"origin\": \"A\", \"destination\": \"B\"}";
			obj = GenericUtil.jsonToObject(json, AvailabilityInboundDto.class);
		}

		@Test
		void checkProperties() throws Exception {
			assertThat(obj, hasProperty("origin"));
			assertThat(obj, hasProperty("destination"));
		}

		@Test
		void checkValues() throws Exception {
			assertThat("A", is(obj.getOrigin()));
			assertThat("B", is(obj.getDestination()));
		}
	}
}