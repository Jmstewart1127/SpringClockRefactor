package com.timeclock.web.ClockBeta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.repository.BusinessRepository;
import com.timeclock.web.ClockBeta.service.BusinessService;
import com.timeclock.web.ClockBeta.service.EmailService;
import com.timeclock.web.ClockBeta.service.JobsService;
import com.timeclock.web.ClockBeta.service.UserService;

@Controller
@SessionAttributes("adminName")
public class BusinessController {

	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	JobsService jobsService;
	
	@Autowired
	UserAuthDetails userAuthDetails;
	
	/*
	 * Shows new business form
	 */
	@RequestMapping(value="/hello/newbusiness", method = RequestMethod.GET)
	public ModelAndView newBiz(ModelAndView modelAndView, Business business, Jobs jobs) {
	    modelAndView.addObject(business);
	    modelAndView.addObject(jobs);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	/*
	 * Adds business 
	 */
	@RequestMapping(value="/hello/newbusiness", method = RequestMethod.POST)
	public ModelAndView addNewBusiness(ModelAndView modelAndView, @Valid Business business, @Valid Jobs jobs,
		BindingResult bindingResult, HttpServletRequest request, Authentication auth) {
		modelAndView.setViewName("registered");
		modelAndView.addObject(business);
		business.setAdminId(userAuthDetails.getUserId(auth));
		business.setAdminName(userAuthDetails.getUserName(auth));
		businessService.saveBusiness(business);
		Jobs j = new Jobs();
		j.setBizId(business.getId());
		j.setJobAddress(jobs.getJobAddress());
		jobsService.saveJobs(j);
		return modelAndView;
	}

	/*
	 * Shows list of businesses
	 */
    @RequestMapping(value="/hello/business", method = RequestMethod.GET)
    public ModelAndView showBusinesses(ModelAndView modelAndView, Business business, Authentication auth) {
        modelAndView.setViewName("showbusinesses");
        modelAndView.addObject("business", businessService.findByCurrentUserId(auth));
        return modelAndView;
    }
	
}
	

