package com.timeclock.web.ClockBeta.repository;

import org.springframework.data.repository.CrudRepository;

import com.timeclock.web.ClockBeta.model.Business;


public interface BusinessRepository extends CrudRepository <Business, Long> {
	
	Business findById(int id);
	
	Business findByBizName(String bizName);
	
	Business findByConfirmationToken(String confirmationToken);
	
	Business findByEmail(String email);
	
	Business findByPassword(String password);
						
	
}
