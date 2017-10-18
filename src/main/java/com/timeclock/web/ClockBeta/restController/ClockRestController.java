package com.timeclock.web.ClockBeta.restController;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping("/rest/employees/{id}")
	public Iterable<Clock> showEmployeesByBizId(@PathVariable int id) {
		return clockService.findByBizId(id);
	}

	/*
	* Gets user by id
	*/
	@RequestMapping("/rest/get/employee/{id}")
	public Iterable<Clock> getEmployee(@PathVariable String id) {
		int result = Integer.parseInt(id);
		return clockService.findById(result);
	}

	/*
	* Gets businessId that employee belongs to.
	*/
	@RequestMapping("/rest/get/business/by/user/{id}")
	public int getBizIdByUserId(@PathVariable String id) {
		int result = Integer.parseInt(id);
		return clockService.findBizIdById(result);
	}

	/*
 	* Employee Clock In
 	*/
	@RequestMapping(value="/rest/clock/in/{id}")
	public void clockIn(@PathVariable int id) {
		clockService.clockIn(id);
	}

	/*
	* Employee Clock Out
 	*/
	@RequestMapping(value="/rest/clock/out/{id}")
	public void clockOut(@PathVariable int id) {
		clockService.clockOut(id);
	}

	/*
	 * Employee Clock In
	 *
	 * This method was the original way of clocking in and out.
	 * However, during testing of the mobile app, it was found
	 * that this method would not get the job done as the app
	 * were intended.
	 *
	 * We wanted an employee's shift to end when they left the
	 * premises, but with this method they would be clocked in
	 * and out while on the premises.
	 */
	@RequestMapping(value="/rest/clock/in/current/status/{id}")
	public String clockInByClockStatus(@PathVariable int id) {
		
		Boolean isClocked = clockService.findClockedById(id);
		
		if (isClocked) {
			clockService.clockOut(id);
			return "Shift End";
		} else {
			clockService.clockIn(id);
			return "Shift Start";
		}
	}
	
}
