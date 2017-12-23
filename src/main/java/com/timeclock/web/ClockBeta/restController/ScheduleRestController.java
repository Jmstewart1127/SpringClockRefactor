package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleRestController {

    @Autowired
    ScheduleService scheduleService;

    /*
    *  Gets all jobs scheduled to user by ID
    */
    @RequestMapping("/rest/jobs/employee/schedule/{clockId}")
    public Iterable<Schedule> showJobAddressByBizId(@PathVariable int clockId) {
        return scheduleService.getScheduleByClockId(clockId);
    }

}
