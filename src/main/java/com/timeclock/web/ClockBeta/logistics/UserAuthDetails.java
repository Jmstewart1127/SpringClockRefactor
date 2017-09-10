package com.timeclock.web.ClockBeta.logistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.timeclock.web.ClockBeta.service.UserService;

public class UserAuthDetails {
	
	@Autowired
	UserService userService;
	
	public String getUserName(Authentication authentication) {
		return authentication.getName();
	}
	
	public int getUserId(Authentication authentication) {
		return userService.getIdByUserName(authentication.getName());
	}
	
}
