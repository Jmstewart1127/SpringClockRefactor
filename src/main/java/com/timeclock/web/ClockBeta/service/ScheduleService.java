package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

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

    
}
