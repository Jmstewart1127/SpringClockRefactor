package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.model.Employee;
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
    EmployeeService employeeService;

    @Autowired
    JobsService jobsService;

    /*
    * find schedule by employee
    */
    public Iterable<Schedule> getScheduleByClockId(int clockId) {
        return scheduleRepository.findScheduleByEmployeeId(clockId);
    }

    /*
    * find schedule by job
    */
    public Iterable<Schedule> getScheduleByJobId(int jobId) {
        return scheduleRepository.findScheduleByEmployeeId(jobId);
    }

    /*
    * find all schedules by business
    */
    public Iterable<Schedule> getScheduleByBizId(int bizId) {
        return scheduleRepository.findScheduleByBusinessId(bizId);
    }

    /*
    * find all job id's of jobs assigned to employee
    * This and the method below could be refactored into a combination of the two.
    */
    public Iterable<Integer> getJobIdsByClockId(int id) {
        return scheduleRepository.findJobIdsByClockId(id);
    }

    /*
    * find all jobs assigned to employee
    */
    public Iterable<Jobs> findJobsAssignedToEmployee(int id) {
        ArrayList<Jobs> jobs = new ArrayList<>();
        for (int jobId : getJobIdsByClockId(id)) {
            if (jobsService.findById(jobId) != null) {
                jobs.add(jobsService.findById(jobId));
            }
        }
        return jobs;
    }

    /*
    * find all employees assigned to job
    */
    public Iterable<Employee> findAllEmployeesOnJob(int jobId) {
        ArrayList<Employee> clock = new ArrayList<>();
        for (int employee : scheduleRepository.findEmployeeIdsByJobId(jobId)) {
            clock.add(employeeService.findEmployeeNameById(employee));
        }
        return clock;
    }

    /*
    * check if schedule already exists
    */
    public boolean checkIfExists(int clockId, int jobId) {
        return scheduleRepository.existsByEmployeeIdAndJobId(clockId, jobId);
    }

    /*
    * delete schedule by id
    */
    public void deleteById(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

}
