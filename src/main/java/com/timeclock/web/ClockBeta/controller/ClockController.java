package com.timeclock.web.ClockBeta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.service.ClockService;

@Controller
@SessionAttributes("user")
public class ClockController {
	
	@Autowired
	ClockService clockService;

	@Autowired
	BusinessService businessService;

	@Autowired
	UserAuthDetails userAuthDetails;

	// Show employees by business id
    @RequestMapping(path="/hello/business/{id}/employees", method = RequestMethod.GET)
    public ModelAndView showClock(ModelAndView modelAndView, Clock clock, Business business, @PathVariable int id) {
    	modelAndView.setViewName("showemployees");
        modelAndView.addObject("clock", clockService.findByBizId(id));
        modelAndView.addObject("business", businessService.findById(id));
        return modelAndView;
    }
    
    // Show all employees
    @RequestMapping(value="/hello/employees", method = RequestMethod.GET)
    public ModelAndView showBusinesses(ModelAndView modelAndView, Clock clock, Business business, Authentication auth) {
        modelAndView.setViewName("showemployees");
        modelAndView.addObject("clock", clockService.findAllEmployeesByAdmin(auth));
        return modelAndView;
    }

    // Add employees to business
	@RequestMapping(path="/hello/business/{id}/adduser", method = RequestMethod.GET)
	public ModelAndView showNewUserForm(ModelAndView modelAndView,
        Clock clock, Business business) {

		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("newuser");
		
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/hello/business/{id}/adduser", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, Business business,
        @Valid Clock clock, BindingResult bindingResult, @PathVariable int id) {
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("newuser");		
		} else {
		    Clock cl = new Clock();
		    cl.setUser(clock.getUser());
			cl.setBizId(id);
			cl.setPayRate(clock.getPayRate());
		    clockService.saveClock(cl);
			modelAndView.setViewName("showemployees");
		}
			
		return this.showClock(modelAndView, clock, business, id);
	}

	// Reset pay period
	@RequestMapping(value = "/hello/business/reset", method = RequestMethod.GET)
	public ModelAndView resetPayPeriod(ModelAndView modelAndView, Business business,
		@Valid Clock clock, Authentication auth) {
    	modelAndView.setViewName("showbusinesses");
		modelAndView.addObject("business", businessService.findByCurrentUserId(auth));
		clockService.resetPayPeriod(userAuthDetails.getUserId(auth));

		return modelAndView;
	}

	// Show homepage clock in form
	@RequestMapping(path="/", method = RequestMethod.GET)
	public ModelAndView showClockForm(ModelAndView modelAndView, Clock clock) {
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("timeclock");
		
		return modelAndView;
	}

	// Process clock in form
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView processClockForm(ModelAndView modelAndView, @Valid Clock clock) {
		modelAndView.setViewName("timeclockupdate");
		int userId = clock.getId();
		Boolean isClocked = clockService.findClockedById(userId);
		
		if (isClocked) {
			clockService.clockOut(userId);
			return modelAndView;
		} else {
			clockService.clockIn(userId);
			return modelAndView;
		}
	}
	
	// Clock in form for 'showemployees' view
	@RequestMapping(value = "/hello/employees/{id}/clockin", method = RequestMethod.POST)
	public ModelAndView processClockFormAdmin(ModelAndView modelAndView, @Valid Clock clock, Business business, @PathVariable int id) {
		Boolean isClocked = clockService.findClockedById(id);
		//int bizId = clockService.findBizIdById(id);
		if (isClocked) {
			clockService.clockOut(id);
			return this.showClock(modelAndView, clock, business, clockService.findBizIdById(id));
		} else {
			clockService.clockIn(id);
			return this.showClock(modelAndView, clock, business, clockService.findBizIdById(id));
		}

	}
	
	// Show update employee form
	@RequestMapping(value="/hello/employee/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateEmployeePage(ModelAndView modelAndView, @PathVariable int id) {
		Clock clock = clockService.findUserById(id);
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("updateemployee");
        return modelAndView;
	}
    
	// Process edit employee form
    @RequestMapping(value="/hello/employee/{id}/update",method=RequestMethod.POST)
	public ModelAndView processEmployeeEditForm(ModelAndView modelAndView,
		@PathVariable int id, @Valid Clock clock, BindingResult bindingResult,
		HttpServletRequest request) {
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("updatejobstatus");		
		} else {
			modelAndView.setViewName("showemployees");
			clockService.saveClock(clock);
		}
			
		return modelAndView;
	}
	
    // Delete user
	@RequestMapping(value = "/hello/employee/{id}/delete", method = RequestMethod.POST)
	public ModelAndView processDeleteFormAdmin(
			ModelAndView modelAndView, 
			@Valid Clock clock, 
			Business business, 
			@PathVariable int id) {
		
		int bizId = clockService.findBizIdById(id); // Store bizId before deleting user
		Clock user = clockService.findUserById(id);
		clockService.delete(user);
		return this.showClock(modelAndView, clock, business, bizId);
	}

}
