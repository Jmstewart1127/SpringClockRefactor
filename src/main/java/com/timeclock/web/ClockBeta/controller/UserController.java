package com.timeclock.web.ClockBeta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.model.User;
import com.timeclock.web.ClockBeta.model.UserRole;
import com.timeclock.web.ClockBeta.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	// Display user registration form
	@RequestMapping(path="/register", method = RequestMethod.GET)
	public ModelAndView showNewUserForm(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registernew");
		
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, 
			@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by id
		User userExists = userService.findById(user.getId());
		
		System.out.println(userExists);
		
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", 
					"Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("newuser");		
		} else {
			UserRole ur = new UserRole();
		    userService.saveUser(user);
		    ur.setUserName(user.getUserName());
		    ur.setRole(user.getRole());
		    userService.saveUserRole(ur);
			modelAndView.addObject(user.getUserName());
			modelAndView.setViewName("registered");
		}
			
		return modelAndView;
	}
	
}

