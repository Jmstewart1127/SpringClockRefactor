package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.service.TimeClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeClockRestController {

    @Autowired
    TimeClockService timeClockService;

}
