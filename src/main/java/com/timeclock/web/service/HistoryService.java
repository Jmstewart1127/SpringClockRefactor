package com.timeclock.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.web.model.History;
import com.timeclock.web.repository.HistoryRepository;

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
	
	public void updateClockOut(int id, Date endTime, long shiftTime, long weeklyTime) {
		historyRepository.updateClock(id, endTime, shiftTime, weeklyTime);
	}
	
}
