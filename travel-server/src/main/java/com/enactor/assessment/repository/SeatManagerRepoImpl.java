package com.enactor.assessment.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.enactor.assessment.dto.ReservationOutBoundDto;
import com.enactor.assessment.dto.SeatInfo;

public class SeatManagerRepoImpl {

	private SeatManagerRepoImpl() { }

	private static class SeatManagerRepoHolder {
		private static Map<String, SeatInfo> seatInfoMap = new ConcurrentHashMap();
		private static Map<String, ReservationOutBoundDto> bookings = new HashMap();
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
	
	public static void persistBookingDetails(ReservationOutBoundDto reservationDetails) {
		SeatManagerRepoHolder.bookings.put(reservationDetails.getBookingReferece(), reservationDetails);
	}
}
