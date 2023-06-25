package com.enactor.assessment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ThreadedSimulation implements Runnable {
	private CountDownLatch latch;
	private Map<String, String> availabilityParams;

	public ThreadedSimulation(CountDownLatch latch) {
		this.latch = latch;
		// setting up input params
		availabilityParams = new HashMap<>();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateTomorrow = LocalDate.now().plusDays(1).format(dateFormatter);
		availabilityParams.put("date", dateTomorrow);
		availabilityParams.put("origin", "A");
		availabilityParams.put("destination", "C");
		availabilityParams.put("passengers", "3");
	}

	@Override
	public void run() {
		try {
			latch.await();
			// System.out.printf("[ %s ] starts at: %s\n", Thread.currentThread().getName(), Instant.now());
			new MainFlow().start(true, availabilityParams);
		} catch (InterruptedException ex) {
			// ex.printStackTrace();
		}
	}
}
