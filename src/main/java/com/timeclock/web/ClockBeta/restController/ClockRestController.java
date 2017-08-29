package com.timeclock.web.ClockBeta.restController;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timeclock.web.ClockBeta.model.Clock;

@RestController
public class ClockRestController {

	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/rest/clockin")
	public Clock clockIn(@RequestParam(value="name", defaultValue="Hi") String name) {
		return new Clock();
	}
	
}
