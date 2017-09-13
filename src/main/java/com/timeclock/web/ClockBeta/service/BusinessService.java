package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.repository.BusinessRepository;

@Service
public class BusinessService {
	
	@Autowired
	BusinessRepository businessRepository;

	@Autowired
    UserAuthDetails userAuthDetails;

	public Business findById(int id) {
		return businessRepository.findById(id);
	}
	
	public Business findByBizName(String bizName) {
		return businessRepository.findByBizName(bizName);
	}

	public Iterable<Business> findByCurrentUserId(Authentication auth) {
	    return businessRepository.findByAdminId(userAuthDetails.getUserId(auth));
    }

    public Iterable<Business> findBusinessesByUserId(int id) {
        return businessRepository.findByAdminId(id);
    }

//    public Iterable<Business> findBusinessesByUserId() {
//	    return this.findByAdminId(auth);
//    }

	public void saveBusiness(Business business) {
		businessRepository.save(business);
	}
	
}
