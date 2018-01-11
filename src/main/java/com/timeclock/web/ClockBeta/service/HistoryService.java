package com.timeclock.web.ClockBeta.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.model.History;
import com.timeclock.web.ClockBeta.repository.HistoryRepository;

@Service
public class HistoryService {
	
	@Autowired
	HistoryRepository historyRepository;

	public Iterable<History> findByUserId(int id) {
		return historyRepository.findByEmployeeId(id);
	}

	public void saveHistory(
			int userId,
			Date startTime,
			Date endTime,
			long shiftTime
			) {
		History h = new History();
		h.setEmployeeId(userId);
		h.setClockInTime(startTime);
		h.setClockOutTime(endTime);
		h.setShiftTime(shiftTime);
		historyRepository.save(h);
	}
	
}
