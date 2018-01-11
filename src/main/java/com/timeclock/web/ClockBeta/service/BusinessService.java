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
	
	public Business findByBusinessName(String businessName) {
		return businessRepository.findByBusinessName(businessName);
	}

	public Iterable<Business> findByLoggedInUserId(Authentication auth) {
	    return businessRepository.findByAdminId(userAuthDetails.getUserId(auth));
    }

    public Iterable<Business> findBusinessesByUserId(int id) {
        return businessRepository.findByAdminId(id);
    }

    public void updateYtdLaborCost(int businessId, double additionalLaborCost) {
		double currentLaborCost = businessRepository.findYtdLaborCostById(businessId);
		double newLaborCost = currentLaborCost + additionalLaborCost;
		businessRepository.updateYtdLaborCost(businessId, newLaborCost);
	}

	public void updateYtdMaterialCost(int businessId, double additionalMaterialCost) {
		double currentMaterialCost = businessRepository.findYtdMaterialCostById(businessId);
		double newMaterialCost = currentMaterialCost + additionalMaterialCost;
		businessRepository.updateYtdMaterialCost(businessId, newMaterialCost);
	}

	public void saveBusiness(Business business) {
		businessRepository.save(business);
	}
	
}
