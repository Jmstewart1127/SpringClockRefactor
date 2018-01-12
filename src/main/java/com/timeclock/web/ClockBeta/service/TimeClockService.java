package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.logistics.TimeLogic;
import com.timeclock.web.ClockBeta.repository.TimeClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimeClockService {

    @Autowired
    TimeClockRepository timeClockRepository;

    @Autowired
    TimeLogic timeLogic;

    @Autowired
    EmployeeService employeeService;

    private boolean checkIfEmployeeIsClockedIn(int employeeId) {
        return employeeService.checkIfEmployeeIsClockedIn(employeeId);
    }

    private void calculateShiftTime(Date startTime, Date endTime) {
        timeLogic.calcShiftTime(startTime, endTime);
    }

    private void calculateWeeklyTime() {
        timeLogic.calcWeeklyTime();
    }

    public void clockIn() {

    }

    public void clockOut() {

    }

}
