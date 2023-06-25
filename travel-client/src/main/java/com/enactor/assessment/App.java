package com.enactor.assessment;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class App {

	public static void main(String[] args) {
		final int numberOfUsers = 6;
		CountDownLatch latch = new CountDownLatch(1);

		IntStream.range(0, numberOfUsers).forEach(num -> {
			Thread myThread = new Thread(new ThreadedSimulation(latch), "my-thread-"+num);
			myThread.start();
		});
		
		latch.countDown();
		
//		new MainFlow().start(false, null);
	}
}
