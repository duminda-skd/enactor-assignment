package com.enactor.assessment.repository;

import java.util.HashMap;
import java.util.Map;

import com.enactor.assessment.dto.SeatInfo;

public class SeatManagerRepoImpl {

	private SeatManagerRepoImpl() { }

	private static class SeatManagerRepoHolder {
		private static Map<String, SeatInfo> seatInfoMap = new HashMap();
	}
	
	public static SeatInfo getSeatInfoByDate(String date) {
		SeatInfo seatInfo = SeatManagerRepoHolder.seatInfoMap.get(date);
		// add to map if it doesn't exist
		if(seatInfo == null) {
			seatInfo = new SeatInfo();
			SeatManagerRepoHolder.seatInfoMap.put(date, seatInfo);
		}
		return seatInfo;
	}
}
