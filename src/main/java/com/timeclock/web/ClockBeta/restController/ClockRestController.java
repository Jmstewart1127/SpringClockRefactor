package com.timeclock.web.ClockBeta.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping("/rest/employees/{id}")
	public Iterable<Clock> showEmployeesByBizId(@PathVariable int id) {
		return clockService.findByBizId(id);
	}

	/*
	* Gets user by id
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping("/rest/get/employee/{id}")
	public Iterable<Clock> getEmployee(@PathVariable int id) {
		return clockService.findById(id);
	}

	/*
	* Employee Clock In (Web)
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/web/clock/in/{id}")
	public void handleClockInOut(@PathVariable int id) {
		clockService.handleClockInOut(id);
	}

	/*
 	* Employee Clock In (Mobile)
 	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/clock/in/{id}")
	public void clockIn(@PathVariable int id) {
		clockService.clockIn(id);
	}

	/*
	* Employee Clock Out (Mobile)
 	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/clock/out/{id}")
	public void clockOut(@PathVariable int id) {
		clockService.clockOut(id);
	}

	/*
	* Refresh Employee Status
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/status/refresh/{id}")
	public void refreshClock(@PathVariable int id) {
		clockService.refreshClock(id);
	}
	
}
