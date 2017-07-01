package com.timeclock.web.controller;

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

import com.timeclock.web.model.Clock;
import com.timeclock.web.model.Jobs;
import com.timeclock.web.service.ClockService;
import com.timeclock.web.service.HistoryService;

@Controller
@SessionAttributes("user")
public class ClockController {
	
	@Autowired
	ClockService clockService;
	
    @RequestMapping(path="/employees", method = RequestMethod.GET)
    public ModelAndView showClock() {
        ModelAndView mav = new ModelAndView("showemployees");
        mav.addObject("clock", clockService.findByBizId(1));
        
        return mav;
    }
	
	@RequestMapping(path="/adduser", method = RequestMethod.GET)
	public ModelAndView showNewUserForm(ModelAndView modelAndView, Clock clock) {
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("newuser");
		
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
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
	
	@RequestMapping(path="/clock", method = RequestMethod.GET)
	public ModelAndView showClockForm(ModelAndView modelAndView, Clock clock) {
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("timeclock");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/clock", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/employee/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateJobsPage(ModelAndView modelAndView, @PathVariable int id) {
		Clock clock = clockService.findById(id);
		modelAndView.addObject("clock", clock);
		modelAndView.setViewName("updateemployee");
        return modelAndView;
	}
    
    @RequestMapping(value="/employee/{id}/update",method=RequestMethod.POST)
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
    
	@RequestMapping(value="/employee/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteJob(ModelAndView modelAndView, @Valid Clock clock, @PathVariable int id) {
		modelAndView.addObject(clock);
		modelAndView.setViewName("showemployees");
		clockService.delete(clock);
        return modelAndView;
	}
	

}
