package com.enactor.assessment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.enactor.assessment.dto.InputValidationFailureResult;
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
		Map<String, String> availabilityInputs;

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
				availabilityInputs = new HashMap<>();
				availabilityInputs.put("origin", "A");
				availabilityInputs.put("destination", "B");
				availabilityInputs.put("passengers", "4");
			}

			@Test
			void checkDateInWrongFormat() throws Exception {
				availabilityInputs.put("date", dateTomorrowWrongFormat);
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, hasSize(1));
				assertThat(results.get(0).getErrorMessage(), containsString("Invalid date. Does not conform to format right format"));
			}

			@Test
			void checkDateInRightFormat() throws Exception {
				availabilityInputs.put("date", dateTomorrow);
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, empty());
			}
		}

		@Nested
		@DisplayName("Tests for the date validating date is after today")
		class DateAfterTodayValidationTest {

			@BeforeEach
			void beforeEach() {
				availabilityInputs = new HashMap<>();
				availabilityInputs.put("origin", "A");
				availabilityInputs.put("destination", "B");
				availabilityInputs.put("passengers", "4");
			}

			@Test
			void checkDateToday() throws Exception {
				availabilityInputs.put("date", dateToday);
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, hasSize(1));
				assertThat(results.get(0).getErrorMessage(), containsString("Please try with a date after today"));
			}

			@Test
			void checkDateAfterToday() throws Exception {
				availabilityInputs.put("date", dateTomorrow);
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, empty());
			}
		}
	}

	@Nested
	@DisplayName("Tests for the origin validation")
	@TestInstance(Lifecycle.PER_CLASS)
	class OriginValidationTest {

		Map<String, String> availabilityInputs;

		@BeforeEach
		void beforeEach() {
			availabilityInputs = new HashMap<>();
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
			availabilityInputs.put("date", dateTomorrow);
			availabilityInputs.put("destination", "B");
			availabilityInputs.put("passengers", "4");
		}

		@Test
		void checkWrongOrigin() throws Exception {
			availabilityInputs.put("origin", "F");
			List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
			assertThat(results, hasSize(1));
			assertThat(results.get(0).getErrorMessage(), containsString("Invalid origin."));
		}

		@Test
		void checkCorrectOrigin() throws Exception {
			availabilityInputs.put("origin", "C");
			List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
			assertThat(results, empty());
		}
	}

	@Nested
	@DisplayName("Tests for the destination validation")
	@TestInstance(Lifecycle.PER_CLASS)
	class DestinationValidationTest {

		@Nested
		@DisplayName("Tests for the correct destination validation")
		@TestInstance(Lifecycle.PER_CLASS)
		class CorrectDestinationValidationTest {

			Map<String, String> availabilityInputs;

			@BeforeEach
			void beforeEach() {
				availabilityInputs = new HashMap<>();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
				availabilityInputs.put("date", dateTomorrow);
				availabilityInputs.put("origin", "A");
				availabilityInputs.put("passengers", "4");
			}

			@Test
			void checkWrongDestination() throws Exception {
				availabilityInputs.put("destination", "F");
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, hasSize(1));
				assertThat(results.get(0).getErrorMessage(), containsString("Invalid destination."));
			}

			@Test
			void checkCorrectDestinationOrigin() throws Exception {
				availabilityInputs.put("destination", "B");
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, empty());
			}
		}

		@Nested
		@DisplayName("Tests for the different origin and destination validation")
		@TestInstance(Lifecycle.PER_CLASS)
		class SameOriginAndDestinationValidationTest {

			Map<String, String> availabilityInputs;

			@BeforeEach
			void beforeEach() {
				availabilityInputs = new HashMap<>();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
				availabilityInputs.put("date", dateTomorrow);
				availabilityInputs.put("origin", "A");
				availabilityInputs.put("passengers", "4");
			}

			@Test
			void checkSameOriginAndDestination() throws Exception {
				availabilityInputs.put("destination", "A");
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, hasSize(1));
				assertThat(results.get(0).getErrorMessage(), containsString("Same origin and destination provided."));
			}

			@Test
			void checkDifferentOriginAndDestination() throws Exception {
				availabilityInputs.put("destination", "B");
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, empty());
			}
		}
	}

	@Nested
	@DisplayName("Tests for the passengers validation")
	@TestInstance(Lifecycle.PER_CLASS)
	class PassengersValidationTest {
		
		@Nested
		@DisplayName("Tests for the correct passenger input format validation")
		@TestInstance(Lifecycle.PER_CLASS)
		class PassengersInputFormatValidationTest {
			
			Map<String, String> availabilityInputs;
			
			@BeforeEach
			void beforeEach() {
				availabilityInputs = new HashMap<>();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
				availabilityInputs.put("date", dateTomorrow);
				availabilityInputs.put("origin", "A");
				availabilityInputs.put("destination", "B");
			}

			@Test
			void checkWrongPassengerCountFormat() throws Exception {
				availabilityInputs.put("passengers", "s");			
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, hasSize(1));
				assertThat(results.get(0).getErrorMessage(), containsString("Invalid passengers count. Not a valid number"));
			}

			@Test
			void checkCorrectPassengerCountFormat() throws Exception {
				availabilityInputs.put("passengers", "2");
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, empty());
			}
		}
		
		@Nested
		@DisplayName("Tests for the correct validation")
		@TestInstance(Lifecycle.PER_CLASS)
		class MoreThanOnePassengersValidationTest {
			
			Map<String, String> availabilityInputs;
			
			@BeforeEach
			void beforeEach() {
				availabilityInputs = new HashMap<>();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
				availabilityInputs.put("date", dateTomorrow);
				availabilityInputs.put("origin", "A");
				availabilityInputs.put("destination", "B");
			}

			@Test
			void checkWrongPassengerCount() throws Exception {
				availabilityInputs.put("passengers", "0");			
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, hasSize(1));
				assertThat(results.get(0).getErrorMessage(), containsString("Invalid passengers count. Has to be a number more than 0"));
			}

			@Test
			void checkCorrectPassengerCount() throws Exception {
				availabilityInputs.put("passengers", "2");
				List<InputValidationFailureResult> results = inputValidator.validateCheckAvailabilityInput(availabilityInputs);
				assertThat(results, empty());
			}
		}
	}
}
