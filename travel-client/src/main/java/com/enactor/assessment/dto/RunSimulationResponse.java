package com.enactor.assessment.dto;

public class RunSimulationResponse {

	private boolean simulationRun;
	private int numberOfUsers;

	public RunSimulationResponse(boolean simulationRun, int numberOfUsers) {
		this.simulationRun = simulationRun;
		this.numberOfUsers = numberOfUsers;
	}

	public boolean isSimulationRun() {
		return simulationRun;
	}

	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	@Override
	public String toString() {
		return "RunSimulationResponse [simulationRun=" + simulationRun + ", numberOfUsers=" + numberOfUsers + "]";
	}
}
