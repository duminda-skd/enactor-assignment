package com.enactor.assessment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.enactor.assessment.dto.AvailabilityInboundDto;
import com.enactor.assessment.dto.ValidationResult;
import com.enactor.assessment.validator.InputValidator;

@TestInstance(Lifecycle.PER_CLASS)
public class InputValidatorTest {
	InputValidator inputValidator;

	@BeforeAll
	void beforeAll() {
		inputValidator = new InputValidator();
	}

	@Nested
	@DisplayName("Tests for the date validation")
	@TestInstance(Lifecycle.PER_CLASS)
	class DateValidationTest {

		String dateToday;
		String dateTomorrow;
		String dateTomorrowWrongFormat;
		AvailabilityInboundDto availabilityInbound;

		@BeforeAll
		void beforeAll() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateToday = LocalDate.now().format(dateFormatter);
			dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
			DateTimeFormatter wrongDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			dateTomorrowWrongFormat = LocalDate.now().plusDays(1).format(wrongDateFormatter);
		}

		@Nested
		@DisplayName("Tests for the date format validation")
		class DateFormatValidationTest {

			@BeforeEach
			void beforeEach() {
				availabilityInbound = new AvailabilityInboundDto();
				availabilityInbound.setOrigin("A");
				availabilityInbound.setDestination("B");
				availabilityInbound.setPassengers(4);
			}

			@Test
			void checkDateInWrongFormat() throws Exception {
				availabilityInbound.setDate(dateTomorrowWrongFormat);
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(result.isValidationFailed());
				assertThat(result.getErrorMessage(),
						containsString("Invalid date. Does not conform to format right format"));
			}

			@Test
			void checkDateInRightFormat() throws Exception {
				availabilityInbound.setDate(dateTomorrow);
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(!result.isValidationFailed());
				assertThat(result.getErrorMessage(), nullValue());
			}
		}

		@Nested
		@DisplayName("Tests for the date validating date is after today")
		class DateAfterTodayValidationTest {

			@BeforeEach
			void beforeEach() {
				availabilityInbound = new AvailabilityInboundDto();
				availabilityInbound.setOrigin("A");
				availabilityInbound.setDestination("B");
				availabilityInbound.setPassengers(4);
			}

			@Test
			void checkDateToday() throws Exception {
				availabilityInbound.setDate(dateToday);
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(result.isValidationFailed());
				assertThat(result.getErrorMessage(), containsString("Please try with a date after today"));
			}

			@Test
			void checkDateAfterToday() throws Exception {
				availabilityInbound.setDate(dateTomorrow);
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(!result.isValidationFailed());
				assertThat(result.getErrorMessage(), nullValue());
			}
		}
	}

	@Nested
	@DisplayName("Tests for the origin validation")
	@TestInstance(Lifecycle.PER_CLASS)
	class OriginValidationTest {

		AvailabilityInboundDto availabilityInbound;

		@BeforeEach
		void beforeEach() {
			availabilityInbound = new AvailabilityInboundDto();
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
			availabilityInbound.setDate(dateTomorrow);
			availabilityInbound.setDestination("B");
			availabilityInbound.setPassengers(4);
		}

		@Test
		void checkWrongOrigin() throws Exception {
			availabilityInbound.setOrigin("F");
			ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
			assertTrue(result.isValidationFailed());
			assertThat(result.getErrorMessage(), containsString("Invalid origin."));
		}

		@Test
		void checkCorrectOrigin() throws Exception {
			availabilityInbound.setOrigin("C");
			ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
			assertTrue(!result.isValidationFailed());
			assertThat(result.getErrorMessage(), nullValue());
		}
	}

	@Nested
	@DisplayName("Tests for the destination validation")
	@TestInstance(Lifecycle.PER_CLASS)
	class DestinationValidationTest {

		@Nested
		@DisplayName("Tests for the correct validation")
		@TestInstance(Lifecycle.PER_CLASS)
		class CorrectValidationTest {

			AvailabilityInboundDto availabilityInbound;

			@BeforeEach
			void beforeEach() {
				availabilityInbound = new AvailabilityInboundDto();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
				availabilityInbound.setDate(dateTomorrow);
				availabilityInbound.setOrigin("A");
				availabilityInbound.setPassengers(4);
			}

			@Test
			void checkWrongDestination() throws Exception {
				availabilityInbound.setDestination("F");
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(result.isValidationFailed());
				assertThat(result.getErrorMessage(), containsString("Invalid destination."));
			}

			@Test
			void checkCorrectDestination() throws Exception {
				availabilityInbound.setDestination("B");
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(!result.isValidationFailed());
				assertThat(result.getErrorMessage(), nullValue());
			}
		}

		@Nested
		@DisplayName("Tests for the different origin and destination validation")
		@TestInstance(Lifecycle.PER_CLASS)
		class SameOriginAndDestinationValidationTest {

			AvailabilityInboundDto availabilityInbound;

			@BeforeEach
			void beforeEach() {
				availabilityInbound = new AvailabilityInboundDto();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
				availabilityInbound.setDate(dateTomorrow);
				availabilityInbound.setOrigin("A");
				availabilityInbound.setPassengers(4);
			}

			@Test
			void checkSameOriginAndDestination() throws Exception {
				availabilityInbound.setDestination("A");
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(result.isValidationFailed());
				assertThat(result.getErrorMessage(), containsString("Same origin and destination provided."));
			}

			@Test
			void checkDifferentOriginAndDestination() throws Exception {
				availabilityInbound.setDestination("B");
				ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
				assertTrue(!result.isValidationFailed());
				assertThat(result.getErrorMessage(), nullValue());
			}
		}
	}

	@Nested
	@DisplayName("Tests for the passengers validation")
	@TestInstance(Lifecycle.PER_CLASS)
	class PassengersValidationTest {
		AvailabilityInboundDto availabilityInbound;

		@BeforeEach
		void beforeEach() {
			availabilityInbound = new AvailabilityInboundDto();
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
			availabilityInbound.setDate(dateTomorrow);
			availabilityInbound.setOrigin("A");
			availabilityInbound.setDestination("B");
		}

		@Test
		void checkWrongPassengerCount() throws Exception {
			availabilityInbound.setPassengers(0);			
			ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
			assertTrue(result.isValidationFailed());
			assertThat(result.getErrorMessage(), containsString("Invalid passengers count."));
		}

		@Test
		void checkCorrectPassengerCount() throws Exception {
			availabilityInbound.setPassengers(2);
			ValidationResult result = inputValidator.validateCheckAvailabilityInput(availabilityInbound);
			assertTrue(!result.isValidationFailed());
			assertThat(result.getErrorMessage(), nullValue());
		}
	}
}
