package com.timeclock.web.ClockBeta.restController;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.service.ClockService;

@RestController
public class ClockRestController {
	
	@Autowired
	ClockService clockService;
	
	/*
	 * Displays all employee data
	 */
	@RequestMapping("/rest/employees") 
	public Iterable<Clock> showEmployeesByBizId() {
		return clockService.findByBizId(1);
	}
	
	/*
	 * Employee Clock In  
	 */
	@RequestMapping(value="/rest/clockin/{id}")
	public void clockIn(@PathVariable int id) {
		int userId = id;
		
		Boolean isClocked = clockService.findClockedById(userId);
		
		if (isClocked) {
			clockService.clockOut(userId);
		} else {
			clockService.clockIn(userId);
		}
	}
	
}
