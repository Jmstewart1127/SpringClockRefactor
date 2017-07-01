package com.timeclock.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.timeclock.web.model.Business;


public interface BusinessRepository extends CrudRepository <Business, Long> {
	
	Business findById(int id);
	
	Business findByBizName(String bizName);
	
	Business findByConfirmationToken(String confirmationToken);
	
	Business findByEmail(String email);
	
	Business findByPassword(String password);
						
	
}
