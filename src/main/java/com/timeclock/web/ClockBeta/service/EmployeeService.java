package com.timeclock.web.ClockBeta.service;

import java.util.ArrayList;
import java.util.Date;

import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.logistics.TimeLogic;
import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import com.timeclock.web.ClockBeta.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	BusinessService businessService;
	
	@Autowired
	HistoryService historyService;

	@Autowired
	JobsService jobsService;
	
	@Autowired
	TimeLogic timeLogic;

	@Autowired 
	UserAuthDetails userAuthDetails;
	
	public void handleClockInOut(int id) {
		if (this.findIsClockedInById(id)) {
			this.clockOut(id);
		} else {
			this.clockIn(id);
		}
	}

	/*
	* Original Employee In Without Job ID
	*/
	public void clockIn(int id) {
		if (!this.findIsClockedInById(id)) {
			Date d = new Date();
			employeeRepository.updateClock(id, d, d);
		}
	}

	/*
	* Original Employee Out Without Job ID
	*/
	public void clockOut(int id) {
		if (this.findIsClockedInById(id)) {
			Date d = new Date();
			Date startTime = employeeRepository.findStartTimeById(id);
			Date lastRefreshTime = employeeRepository.findLastRefreshById(id);
			long currentWeek = employeeRepository.findWeekTimeById(id);
			timeLogic.setWeeklyTime(currentWeek);
			long shift = timeLogic.getShiftTime();
			double payRate = employeeRepository.findPayRateById(id);
			double exactWeeklyTime = timeLogic.longToDoubleInHours(timeLogic.getWeeklyTime());
			double weeklyHours = timeLogic.timeToHours(timeLogic.getWeeklyTime());
			double weeklyPay = timeLogic.calculatePay(exactWeeklyTime, payRate);
			timeLogic.calcShiftTime(lastRefreshTime, d);
			timeLogic.calcWeeklyTime(currentWeek, shift);
			employeeRepository.updateClock(id, d, timeLogic.getShiftTime(), timeLogic.getWeeklyTime(), weeklyHours, weeklyPay);
			historyService.saveHistory(id, startTime, d, shift);
		}
	}

	/*
	* Employee In With Job ID
	*/
	public void clockInAtJob(int id, int jobId) {
		if (!this.findIsClockedInById(id)) {
			Date d = new Date();
			employeeRepository.clockIn(id, jobId, d, d);
		}
	}

	/*
	* Employee Out With Job ID
	*/
	public void clockOutFromJob(int id, int jobId) {
		if (this.findIsClockedInById(id)) {
			Date d = new Date();
			Date startTime = employeeRepository.findStartTimeById(id);
			Date lastRefreshTime = employeeRepository.findLastRefreshById(id);
			if (lastRefreshTime.after(startTime)) {
				timeLogic.calcShiftTime(lastRefreshTime, d);
			} else {
				timeLogic.calcShiftTime(startTime, d);
			}
			double shiftPay = timeLogic.calculatePay(
					timeLogic.longToDoubleInHours(timeLogic.getShiftTime()),
					employeeRepository.findPayRateById(id)
			);
			timeLogic.calcWeeklyTime(employeeRepository.findWeekTimeById(id), timeLogic.getShiftTime());
			double weeklyPay = timeLogic.calculatePay(
					timeLogic.longToDoubleInHours(timeLogic.getWeeklyTime()),
					employeeRepository.findPayRateById(id)
			);
			employeeRepository.clockOut(
					id,
					jobId,
					d,
					timeLogic.getShiftTime(),
					timeLogic.getWeeklyTime(),
					timeLogic.timeToHours(timeLogic.getWeeklyTime()),
					weeklyPay
			);
			jobsService.updateLaborCost(jobId, shiftPay);
			historyService.saveHistory(id, startTime, d, timeLogic.getShiftTime());
		}
	}

	/*
	* Refresh with Job ID... Adds labor cost upon refresh
	*/
	public void refreshClockAndAddLabor(int id) {
		if (this.findIsClockedInById(id)) {
			Date d = new Date();
			Date startTime = employeeRepository.findStartTimeById(id);
			Date lastRefreshTime = employeeRepository.findLastRefreshById(id);
			if (lastRefreshTime.after(startTime)) {
				timeLogic.calcShiftTime(lastRefreshTime, d);
			} else {
				timeLogic.calcShiftTime(startTime, d);
			}
			int jobId = findClockedInAtById(id);
			double shiftPay = timeLogic.calculatePay(timeLogic.longToDoubleInHours(timeLogic.getShiftTime()), employeeRepository.findPayRateById(id));
			timeLogic.calcWeeklyTime(employeeRepository.findWeekTimeById(id), timeLogic.getShiftTime());
			double weeklyPay = timeLogic.calculatePay(timeLogic.longToDoubleInHours(timeLogic.getWeeklyTime()), employeeRepository.findPayRateById(id));
			employeeRepository.refreshClockWithJobId(id, timeLogic.getShiftTime(), timeLogic.getWeeklyTime(), jobId, timeLogic.timeToHours(timeLogic.getWeeklyTime()), weeklyPay, d);
			jobsService.updateLaborCost(jobId, shiftPay);
			historyService.saveHistory(id, startTime, d, timeLogic.getShiftTime());
		}
	}

	/*
	* Refresh without job id for mobile/rest
	*/
	public void refreshClock(int id) {
		if (this.findIsClockedInById(id)) {
			Date d = new Date();
			Date lastRefreshTime = employeeRepository.findLastRefreshById(id);
			long currentWeek = employeeRepository.findWeekTimeById(id);
			timeLogic.setWeeklyTime(currentWeek);
			double payRate = employeeRepository.findPayRateById(id);
			double exactWeeklyTime = timeLogic.longToDoubleInHours(timeLogic.getWeeklyTime());
			double weeklyHours = timeLogic.timeToHours(timeLogic.getWeeklyTime());
			double weeklyPay = timeLogic.calculatePay(exactWeeklyTime, payRate);
			timeLogic.calcShiftTime(lastRefreshTime, d);
			timeLogic.calcWeeklyTime(currentWeek, timeLogic.getShiftTime());
			employeeRepository.refreshClock(id, timeLogic.getShiftTime(), timeLogic.getWeeklyTime(), weeklyHours, weeklyPay, d);
		}
	}

	/*
	* Finds all employees by logged in user for web application
	*/
	public Iterable<Employee> findAllEmployeesByAdmin(Authentication auth) {
		Iterable<Business> usersBusinesses = businessService.findByLoggedInUserId(auth);
		ArrayList<Employee> allEmployees = new ArrayList<Employee>();
		for (Business business : usersBusinesses) {
			Iterable<Employee> employeesFound = this.findEmployeeByBusinessId(business.getId());
			for (Employee clocks : employeesFound) {
				allEmployees.add(clocks);
			}
		}
		return allEmployees;
	}

	/*
	* Finds all employees by user id for rest controller
	*/
	public Iterable<Employee> findAllEmployeesByAdminId(int id) {
		Iterable<Business> usersBusinesses = businessService.findBusinessesByUserId(id);
		ArrayList<Employee> allEmployees = new ArrayList<Employee>();
		for (Business business : usersBusinesses) {
			Iterable<Employee> employeesFound = this.findEmployeeByBusinessId(business.getId());
			for (Employee clocks : employeesFound) {
				allEmployees.add(clocks);
			}
		}
		return allEmployees;
	}

	/*
	* Delete user by id
	*/
	public void deleteById(int id) {
		employeeRepository.delete(findEmployeeNameById(id));
	}

	public void resetPayPeriod(int bizId) {
		employeeRepository.resetClock(bizId);
	}
	
	public Iterable<Employee> findEmployeeByBusinessId(int bizId) {
		return employeeRepository.findByBusinessId(bizId);
	}
	
	public Boolean findIsClockedInById(int id) {
		return employeeRepository.findIsClockedInById(id);
	}
	
	public Iterable<Employee> findById(int id) {
		return employeeRepository.findById(id);
	}
	
	public Employee findEmployeeNameById(int id) {
		return employeeRepository.findEmployeeNameById(id);
	}
	
	public Employee findByEmployeeName(String employeeName) {
		return employeeRepository.findByEmployeeName(employeeName);
	}

	public int findClockedInAtById(int id) {
		return employeeRepository.findJobIdClockedInAtById(id);
	}
	
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public int findBusinessIdById(int id) {
		return employeeRepository.findBusinessIdById(id);
	}
}
