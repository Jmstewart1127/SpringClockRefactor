package com.timeclock.web.ClockBeta.controller;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.service.ClockService;
import com.timeclock.web.ClockBeta.service.JobsService;
import com.timeclock.web.ClockBeta.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    JobsService jobsService;

    @Autowired
    ClockService clockService;

    @RequestMapping(value="/hello/jobs/{id}/add/employees", method = RequestMethod.GET)
    public ModelAndView showUpdateJobsPage(
            ModelAndView modelAndView,
            Authentication auth,
            @Valid Jobs jobs,
            @Valid Clock clock,
            @Valid Schedule schedule) {
        modelAndView.addObject("jobs", jobs);
        modelAndView.addObject("clock", clockService.findAllEmployeesByAdmin(auth));
        modelAndView.addObject("schedule", schedule);
        modelAndView.setViewName("scheduleemployees");
        return modelAndView;
    }

    @RequestMapping(value="/hello/jobs/{id}/add/employees",method=RequestMethod.POST)
    public ModelAndView processJobEditForm(
            ModelAndView modelAndView,
            @PathVariable int id,
            @RequestParam List<Integer> clockIds,
            BindingResult bindingResult,
            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("updatejobstatus");
        } else {
            modelAndView.setViewName("showjobs");
            for (int clockId : clockIds) {
                Schedule s = new Schedule();
                s.setJobId(id);
                s.setBizId(jobsService.findById(id).getBizId());
                s.setClockId(clockId);
                scheduleService.saveSchedule(s);
            }

        }

        return modelAndView;
    }
}
