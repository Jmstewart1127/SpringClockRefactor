package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Jobs;
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
    *  Gets all jobs scheduled to user by Business ID
    */
    @RequestMapping("/rest/jobs/employee/schedule/{clockId}")
    public Iterable<Schedule> showScheduleByClockId(@PathVariable int clockId) {
        return scheduleService.getScheduleByClockId(clockId);
    }

    /*
    *  Gets all job addresses by business ID
    */
    @RequestMapping("/rest/jobs/assigned/employee/{clockId}")
    public Iterable<Jobs> showJobsAssignedToEmployee(@PathVariable int clockId) {
        return scheduleService.findJobsAssignedToEmployee(clockId);
    }

    /*
    * Gets all job ID's assigned to user
    */
    @RequestMapping("/rest/jobs/employee/schedule/jobs/{clockId}")
    public Iterable<Integer> showJobAddressByClockId(@PathVariable int clockId) {
        return scheduleService.getJobIdsByClockId(clockId);
    }
}
