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
	
	public void saveHistory(History history) {
		historyRepository.save(history);
	}
	
	public void addNewUser(int bizId, String user) {
		History h = new History();
		h.setBizId(bizId);
		h.setUser(user);
		historyRepository.save(h);
	}

	public void updateClockIn(int id, Date startTime) {
		historyRepository.updateClock(id, startTime);
	}
	
	public void saveHistory(
			int userId,
			Date startTime,
			Date endTime,
			long shiftTime
			) {
		History h = new History();
		h.setUserId(userId);
		h.setClockIn(startTime);
		h.setClockOut(endTime);
		h.setShiftTime(shiftTime);
		historyRepository.save(h);
	}
	
}
