package com.enactor.assessment;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.enactor.assessment.constant.MainFlowConstant;
import com.enactor.assessment.dto.AvailabilityDto;
import com.enactor.assessment.dto.InputValidationFailureResult;
import com.enactor.assessment.service.InputService;
import com.enactor.assessment.service.InputServiceImpl;
import com.enactor.assessment.service.TravelService;
import com.enactor.assessment.service.TravelServiceImpl;
import com.enactor.assessment.validator.InputValidator;

public class App implements MainFlowConstant {

	public static void main(String[] args) {
		// defining scanner once and for all
		Scanner myScanner = new Scanner(System.in);
		boolean terminate = false;

		while (!terminate) {
			// working on getting availability input
			InputService inputService = new InputServiceImpl();
			Map<String, String> availabilityParams = inputService.getAvailabilityInput(myScanner);

			// do validation
			InputValidator inputValidator = new InputValidator();
			List<InputValidationFailureResult> validationResults = inputValidator
					.validateCheckAvailabilityInput(availabilityParams);
			if(validationResults.size() > 0) {
				validationResults.forEach(result -> System.out.println(result.getErrorMessage()));
				System.out.println(TRY_AGAIN_AFTER_VALIDATION_MESSAGE);
				continue;
			}
			
			TravelService travelService = new TravelServiceImpl();

			// working on checking availability
			AvailabilityDto availabilityCheck = travelService.doAvailabilityCheck(availabilityParams);
			if (availabilityCheck.isSuccess()) {
				// working on reservation
				boolean userConfirmed = inputService.getUserConfirmationForReservation(myScanner);
				if (userConfirmed) {
					travelService.doTicketReserve(availabilityParams);
				}
			}

			// working on getting availability input
			terminate = inputService.evalTermination(myScanner);
		}
	}
}
