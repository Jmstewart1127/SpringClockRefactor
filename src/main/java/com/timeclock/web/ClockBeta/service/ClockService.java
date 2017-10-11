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
	
	public void clockIn(int id) {
		Date d = new Date();
		clockRepository.updateClock(id, d);
	}
	
	public void clockOut(int id) {
		Date d = new Date();
		cl.endShift(clockRepository.findStartTimeById(id), d);
		long currentWeek = clockRepository.findWeekTimeById(id);
		long shift = cl.getShiftTime();
		double payRate = clockRepository.findPayRateById(id);
		cl.calcWeeklyTime(currentWeek, shift);
		double exactWeeklyTime = cl.longToDoubleInHours(cl.getWeeklyTime());
		System.out.println(exactWeeklyTime);
		double weeklyHours = cl.timeToHours(cl.getWeeklyTime());
		double weeklyPay = cl.calculatePay(exactWeeklyTime, payRate);
		clockRepository.updateClock(id, d, cl.getShiftTime(), cl.getWeeklyTime(), weeklyHours, weeklyPay);
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
	
	public Clock findById(int id) {
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
