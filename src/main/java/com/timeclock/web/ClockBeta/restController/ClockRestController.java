package com.timeclock.web.ClockBeta.restController;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	 * Displays all employee data by business id
	 */
	@RequestMapping("/rest/employees/{id}")
	public Iterable<Clock> showEmployeesByBizId(@PathVariable int id) {
		return clockService.findByBizId(id);
	}
	
	/*
	 * Employee Clock In  
	 */
	@RequestMapping(value="/rest/clockin/{id}")
	public String clockIn(@PathVariable int id) {
		int userId = id;
		
		Boolean isClocked = clockService.findClockedById(userId);
		
		if (isClocked) {
			clockService.clockOut(userId);
			return "Shift End";
		} else {
			clockService.clockIn(userId);
			return "Shift Start";
		}
	}
	
}
