package com.timeclock.web.ClockBeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.repository.BusinessRepository;

@Service
public class BusinessService {
	
	@Autowired
	BusinessRepository businessRepository;

	public Business findById(int id) {
		return businessRepository.findById(id);
	}
	
	public Business findByBizName(String bizName) {
		return businessRepository.findByBizName(bizName);
	}

	public Iterable<Business> findByAdminId(int adminId) {
	    return businessRepository.findByAdminId(adminId);
    }

	public void saveBusiness(Business business) {
		businessRepository.save(business);
	}
	
	
	
}
