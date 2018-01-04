package com.timeclock.web.ClockBeta.service;

import java.util.ArrayList;
import java.util.Date;

import com.timeclock.web.ClockBeta.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.logistics.ClockLogic;
import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.repository.ClockRepository;

@Service
public class ClockService {
	
	@Autowired
	ClockRepository clockRepository;

	@Autowired
	BusinessService businessService;
	
	@Autowired
	HistoryService historyService;

	@Autowired
	JobsService jobsService;
	
	@Autowired
	ClockLogic cl;

	@Autowired 
	UserAuthDetails userAuthDetails;
	
	public void handleClockInOut(int id) {
		if (this.findClockedById(id)) {
			this.clockOut(id);
		} else {
			this.clockIn(id);
		}
	}

	/*
	* Original Clock In Without Job ID
	*/
	public void clockIn(int id) {
		if (!this.findClockedById(id)) {
			Date d = new Date();
			clockRepository.updateClock(id, d, d);
		}
	}

	/*
	* Original Clock Out Without Job ID
	*/
	public void clockOut(int id) {
		if (this.findClockedById(id)) {
			Date d = new Date();
			Date startTime = clockRepository.findStartTimeById(id);
			Date lastRefreshTime = clockRepository.findLastRefreshById(id);
			long currentWeek = clockRepository.findWeekTimeById(id);
			cl.setWeeklyTime(currentWeek);
			long shift = cl.getShiftTime();
			double payRate = clockRepository.findPayRateById(id);
			double exactWeeklyTime = cl.longToDoubleInHours(cl.getWeeklyTime());
			double weeklyHours = cl.timeToHours(cl.getWeeklyTime());
			double weeklyPay = cl.calculatePay(exactWeeklyTime, payRate);
			cl.calcShiftTime(lastRefreshTime, d);
			cl.calcWeeklyTime(currentWeek, shift);
			clockRepository.updateClock(id, d, cl.getShiftTime(), cl.getWeeklyTime(), weeklyHours, weeklyPay);
			historyService.saveHistory(id, startTime, d, shift);
		}
	}

	/*
	* Clock In With Job ID
	*/
	public void clockInAtJob(int id, int jobId) {
		if (!this.findClockedById(id)) {
			Date d = new Date();
			clockRepository.clockIn(id, jobId, d, d);
		}
	}

	/*
	* Clock Out With Job ID... Sorry for long lines; sacrificed less lines for longer lines.
	*/
	public void clockOutFromJob(int id, int jobId) {
		if (this.findClockedById(id)) {
			Date d = new Date();
			Date startTime = clockRepository.findStartTimeById(id);
			Date lastRefreshTime = clockRepository.findLastRefreshById(id);
			if (lastRefreshTime.after(startTime)) {
				cl.calcShiftTime(lastRefreshTime, d);
			} else {
				cl.calcShiftTime(startTime, d);
			}
			double shiftPay = cl.calculatePay(cl.longToDoubleInHours(cl.getShiftTime()), clockRepository.findPayRateById(id));
			cl.calcWeeklyTime(clockRepository.findWeekTimeById(id), cl.getShiftTime());
			double weeklyPay = cl.calculatePay(cl.longToDoubleInHours(cl.getWeeklyTime()), clockRepository.findPayRateById(id));
			clockRepository.clockOut(
					id,
					jobId,
					d,
					cl.getShiftTime(),
					cl.getWeeklyTime(),
					cl.timeToHours(cl.getWeeklyTime()),
					weeklyPay
			);
			jobsService.updateLaborCost(jobId, shiftPay);
			historyService.saveHistory(id, startTime, d, cl.getShiftTime());
		}
	}

	/*
	* Refresh with Job ID... Adds labor cost upon refresh
	*/
	public void refreshClockAndAddLabor(int id, int jobId) {
		if (this.findClockedById(id)) {
			Date d = new Date();
			Date startTime = clockRepository.findStartTimeById(id);
			Date lastRefreshTime = clockRepository.findLastRefreshById(id);
			if (lastRefreshTime.after(startTime)) {
				cl.calcShiftTime(lastRefreshTime, d);
			} else {
				cl.calcShiftTime(startTime, d);
			}
			double shiftPay = cl.calculatePay(cl.longToDoubleInHours(cl.getShiftTime()), clockRepository.findPayRateById(id));
			cl.calcWeeklyTime(clockRepository.findWeekTimeById(id), cl.getShiftTime());
			double weeklyPay = cl.calculatePay(cl.longToDoubleInHours(cl.getWeeklyTime()), clockRepository.findPayRateById(id));
			clockRepository.refreshClockWithJobId(id, cl.getShiftTime(), cl.getWeeklyTime(), jobId, cl.timeToHours(cl.getWeeklyTime()), weeklyPay, d);
			jobsService.updateLaborCost(jobId, shiftPay);
			historyService.saveHistory(id, startTime, d, cl.getShiftTime());
		}
	}

	/*
	* Refresh without job id for mobile/rest
	*/
	public void refreshClock(int id) {
		if (this.findClockedById(id)) {
			Date d = new Date();
			Date lastRefreshTime = clockRepository.findLastRefreshById(id);
			long currentWeek = clockRepository.findWeekTimeById(id);
			cl.setWeeklyTime(currentWeek);
			double payRate = clockRepository.findPayRateById(id);
			double exactWeeklyTime = cl.longToDoubleInHours(cl.getWeeklyTime());
			double weeklyHours = cl.timeToHours(cl.getWeeklyTime());
			double weeklyPay = cl.calculatePay(exactWeeklyTime, payRate);
			cl.calcShiftTime(lastRefreshTime, d);
			cl.calcWeeklyTime(currentWeek, cl.getShiftTime());
			clockRepository.refreshClock(id, cl.getShiftTime(), cl.getWeeklyTime(), weeklyHours, weeklyPay, d);
		}
	}

	/*
	* Finds all employees by logged in user for web application
	*/
	public Iterable<Clock> findAllEmployeesByAdmin(Authentication auth) {
		Iterable<Business> usersBusinesses = businessService.findByCurrentUserId(auth);
		ArrayList<Clock> allEmployees = new ArrayList<Clock>();
		for (Business business : usersBusinesses) {
			Iterable<Clock> employeesFound = this.findByBizId(business.getId());
			for (Clock clocks : employeesFound) {
				allEmployees.add(clocks);
			}
		}
		return allEmployees;
	}

	/*
	* Finds all employees by user id for rest controller
	*/
	public Iterable<Clock> findAllEmployeesByAdminId(int id) {
		Iterable<Business> usersBusinesses = businessService.findBusinessesByUserId(id);
		ArrayList<Clock> allEmployees = new ArrayList<Clock>();
		for (Business business : usersBusinesses) {
			Iterable<Clock> employeesFound = this.findByBizId(business.getId());
			for (Clock clocks : employeesFound) {
				allEmployees.add(clocks);
			}
		}
		return allEmployees;
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
	
	public Clock findUserById(int id) {
		return clockRepository.findUserById(id);
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
