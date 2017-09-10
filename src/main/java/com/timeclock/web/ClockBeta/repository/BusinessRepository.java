package com.timeclock.web.ClockBeta.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.timeclock.web.ClockBeta.model.Business;
import org.springframework.data.repository.query.Param;


public interface BusinessRepository extends CrudRepository <Business, Long> {
	
	Business findById(int id);
	
	Business findByBizName(String bizName);

	Iterable<Business> findByAdminId(int adminId);
	
}
