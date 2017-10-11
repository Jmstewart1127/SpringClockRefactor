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
	
	public void updateClockOut(int id, Date startTime, Date endTime, long shiftTime, long weeklyTime) {
		historyRepository.updateClock(id, startTime, endTime, shiftTime, weeklyTime);
	}
	
}
