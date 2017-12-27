package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    JobsService jobsService;

    /*
    * find schedule by employee
    */
    public Iterable<Schedule> getScheduleByClockId(int id) {
        return scheduleRepository.findScheduleByClockId(id);
    }

    /*
    * find all schedules by business
    */
    public Iterable<Schedule> getScheduleByBizId(int id) {
        return scheduleRepository.findScheduleByBizId(id);
    }

    /*
    * find all jobs assigned to employee
    */
    public Iterable<Integer> getJobIdsByClockId(int id) {
        return scheduleRepository.findJobIdsByClockId(id);
    }

    /*
    * check if schedule already exists
    */
    public boolean checkIfExists(int clockId, int jobId) {
        return scheduleRepository.existsByClockIdAndJobId(clockId, jobId);
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

}
