package com.timeclock.web.ClockBeta.service;

import java.util.Date;

import com.timeclock.web.ClockBeta.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.logistics.ClockLogic;
import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.repository.ClockRepository;

@Service
public class ClockService {
	
	@Autowired
	ClockRepository clockRepository;

	@Autowired
	ClockLogic cl;

	@Autowired
	HistoryService historyService;
	
	public void clockIn(int id) {
		if (!this.findClockedById(id)) {
			Date d = new Date();
			clockRepository.updateClock(id, d, d);
		} else {
			System.out.print("User clocked in");
		}
	}
	
	public void clockOut(int id) {
		if (this.findClockedById(id)) {
			Date d = new Date();
			Date startTime = clockRepository.findStartTimeById(id);
			Date lastRefreshTime = clockRepository.findlastRefreshById(id);
			long currentWeek = clockRepository.findWeekTimeById(id);
			cl.setWeeklyTime(currentWeek);
			long shift = cl.getShiftTime();
			double payRate = clockRepository.findPayRateById(id);
			double exactWeeklyTime = cl.longToDoubleInHours(cl.getWeeklyTime());
			double weeklyHours = cl.timeToHours(cl.getWeeklyTime());
			double weeklyPay = cl.calculatePay(exactWeeklyTime, payRate);
			cl.endShift(lastRefreshTime, d);
			cl.calcWeeklyTime(currentWeek, shift);
			clockRepository.updateClock(id, d, cl.getShiftTime(), cl.getWeeklyTime(), weeklyHours, weeklyPay);
			historyService.saveHistory(id, startTime, d, shift); // add time to hours sometime
		}
	}

	public void refreshClock(int id) {
		if (this.findClockedById(id)) {
			Date d = new Date();
			Date lastRefreshTime = clockRepository.findlastRefreshById(id);
			long currentWeek = clockRepository.findWeekTimeById(id);
			cl.setWeeklyTime(currentWeek);
			long shift = cl.getShiftTime();
			double payRate = clockRepository.findPayRateById(id);
			double exactWeeklyTime = cl.longToDoubleInHours(cl.getWeeklyTime());
			double weeklyHours = cl.timeToHours(cl.getWeeklyTime());
			double weeklyPay = cl.calculatePay(exactWeeklyTime, payRate);
			cl.endShift(lastRefreshTime, d);
			cl.calcWeeklyTime(currentWeek, shift);
			clockRepository.refreshClock(id, cl.getShiftTime(), cl.getWeeklyTime(), weeklyHours, weeklyPay, d);
		}
	}

	public void resetPayPeriod(int bizId) {
		clockRepository.resetClock(bizId);
	}
	
	public Iterable<Clock> findByBizId(int bizId) {
		return clockRepository.findByBizId(bizId);
	}
	
	public Boolean findClockedById(int id) {
		return clockRepository.findClockedById(id);
	}
	
	public Iterable<Clock> findById(int id) {
		return clockRepository.findById(id);
	}
	
	public Clock findByUser(String user) {
		return clockRepository.findByUser(user);
	}
	
	public Clock findByClocked(Boolean clocked) {
		return clockRepository.findByClocked(clocked);
	}
	
	public void delete(Clock clock) {
		clockRepository.delete(clock);
	}
	
	public Clock saveClock(Clock clock) {
		return clockRepository.save(clock);
	}
	
	public int findBizIdById(int id) {
		return clockRepository.findBizIdById(id);
	}
}
