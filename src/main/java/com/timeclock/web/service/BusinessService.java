package com.timeclock.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.web.model.Business;
import com.timeclock.web.repository.BusinessRepository;

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
	
	public Business findByConfirmationToken(String confirmationToken) {
		return businessRepository.findByConfirmationToken(confirmationToken);
	}
	
	public Business findByEmail(String email) {
		return businessRepository.findByEmail(email);
	}
	
	public Business findByPassword(String password) {
		return businessRepository.findByPassword(password);
	}
	
	public void saveBusiness(Business business) {
		businessRepository.save(business);
	}
	
	
	
}
