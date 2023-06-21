package com.enactor.assessment.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PricingMapGenerator {

	private PricingMapGenerator() { }

	private static class PricingMapGeneratorHolder {
		private static Map<Set<String>, Double> pricingMap = new HashMap();

		// Defining unique destination combinations (order shouldn't matter here)
		private static final Set<String> AB = Set.of("A", "B");
		private static final Set<String> AC = Set.of("A", "C");
		private static final Set<String> AD = Set.of("A", "D");
		private static final Set<String> BC = Set.of("B", "C");
		private static final Set<String> BD = Set.of("B", "D");
		private static final Set<String> CD = Set.of("C", "D");

		// defining prices for the above combinations
		static {
			pricingMap.put(AB, 50.0);
			pricingMap.put(AC, 100.0);
			pricingMap.put(AD, 150.0);
			pricingMap.put(BC, 50.0);
			pricingMap.put(BD, 100.0);
			pricingMap.put(CD, 100.0);
		}
	}

	public static Map<Set<String>, Double> getPricingMap() {
		return PricingMapGeneratorHolder.pricingMap;
	}
}
