package com.enactor.assessment;

import com.enactor.assessment.dto.RunSimulationResponse;
import com.enactor.assessment.service.InputService;
import com.enactor.assessment.service.InputServiceImpl;

public class App {

	public static void main(String[] args) {
		InputService inputService = new InputServiceImpl();
		RunSimulationResponse userResponse = inputService.evalRunSimulation();
		if (userResponse.isSimulationRun()) {
			new ThreadedSimulation().runSimulation(userResponse.getNumberOfUsers());
		} else {
			new MainFlow().startNormalFlow();
		}
	}
}
