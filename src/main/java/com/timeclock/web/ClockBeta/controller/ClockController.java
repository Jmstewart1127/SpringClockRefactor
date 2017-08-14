package com.timeclock.web.ClockBeta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.service.ClockService;

@Controller
@SessionAttributes("user")
public class ClockController {
	
	@Autowired
	ClockService clockService;
	
    @RequestMapping(path="/hello/employees", method = RequestMethod.GET)
    public ModelAndView showClock(ModelAndView modelAndView, Clock clock) {
        modelAndView.setViewName("showemployees");
        modelAndView.addObject("clock", clockService.findByBizId(1));
        
        return modelAndView;
    }
	
	@RequestMapping(path="/hello/adduser", method = RequestMethod.GET)
	public ModelAndView showNewUserForm(ModelAndView modelAndView, Clock clock) {
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("newuser");
		
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/hello/adduser", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, 
			@Valid Clock clock, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by id
		Clock clockExists = clockService.findById(clock.getId());
		
		System.out.println(clockExists);
		
		if (clockExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", 
					"Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("newuser");		
		} else {
		    clockService.saveClock(clock);
			modelAndView.addObject(clock.getUser());
			modelAndView.setViewName("useradded");
		}
			
		return modelAndView;
	}
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public ModelAndView showClockForm(ModelAndView modelAndView, Clock clock) {
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("timeclock");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView processClockForm(ModelAndView modelAndView, 
		@Valid Clock clock, BindingResult bindingResult, HttpServletRequest request) {
		
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
	public ModelAndView processClockFormAdmin(ModelAndView modelAndView, @Valid Clock clock,
		@PathVariable int id, BindingResult bindingResult, HttpServletRequest request) {
		
		int userId = id;
		
		Boolean isClocked = clockService.findClockedById(userId);
		
		if (isClocked) {
			clockService.clockOut(userId);
			return this.showClock(modelAndView, clock);
		} else {
			clockService.clockIn(userId);
			return this.showClock(modelAndView, clock);
		}

	}
	
	@RequestMapping(value="/hello/employee/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateJobsPage(ModelAndView modelAndView, @PathVariable int id) {
		Clock clock = clockService.findById(id);
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("updateemployee");
        return modelAndView;
	}
    
    @RequestMapping(value="/hello/employee/{id}/update",method=RequestMethod.POST)
	public ModelAndView processJobEditForm(ModelAndView modelAndView,
			@PathVariable int id,@Valid Clock clock, BindingResult bindingResult, 
			HttpServletRequest request) {
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("updatejobstatus");		
		} else { 
 
			modelAndView.setViewName("showemployees");
			clockService.saveClock(clock);
		}
			
		return modelAndView;
	}
    
	@RequestMapping(value="/hello/employee/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteJob(ModelAndView modelAndView, @Valid Clock clock, @PathVariable int id) {
		modelAndView.addObject(clock);
		modelAndView.setViewName("showemployees");
		clockService.delete(clock);
        return modelAndView;
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
    public ModelAndView viewTest(ModelAndView modelAndView) {
		modelAndView.setViewName("basic_table");
        return modelAndView;
	}
	
	@RequestMapping(value="/hello/test2", method = RequestMethod.GET)
    public ModelAndView testTwo(ModelAndView modelAndView) {
        modelAndView.setViewName("showemployees");
        modelAndView.addObject("clock", clockService.findByBizId(1));
        
        return modelAndView;
	}
	

}
