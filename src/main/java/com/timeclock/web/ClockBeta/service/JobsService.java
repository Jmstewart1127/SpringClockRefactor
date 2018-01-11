package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.logistics.PaymentLogic;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.repository.JobsRepository;

@Service
public class JobsService {

	@Autowired
	JobsRepository jobsRepository;

	@Autowired
	ScheduleService scheduleService;
	
	public Jobs findById(int id) {
		return jobsRepository.findById(id);
	}
	
	public Iterable<Jobs> findAll() {
		return jobsRepository.findAll();
	}

	public Iterable<Jobs> findByBizId(int bizId) {
		return jobsRepository.findByBusinessId(bizId);
	}

	public Iterable<Jobs> findAddressByBizId(int bizId) {
		return jobsRepository.findAddressByBusinessId(bizId);
	}
	
	public Jobs findByCategory(String category) {
		return jobsRepository.findByCategory(category);
	}
	
	public Jobs findByCustomerName(String customer) {
		return jobsRepository.findByCustomerName(customer);
	}
	
	public double findTotalAmountChargedById(int id) {
		return jobsRepository.findAmountChargedById(id);
	}
	
	public double findBalanceDueById(int id) {
		return jobsRepository.findAmountDueById(id);
	}
	
	public double findAmountPaidById(int id) {
		return jobsRepository.findAmountPaidById(id);
	}
	
	public void updateAmountDueById(int id, double amountPaid, double amountDue) {
		jobsRepository.updateAmountDue(id, amountPaid, amountDue);
	}
	
	public int findIdByCustomerName(String customerName) {
		return jobsRepository.findIdByCustomerName(customerName);
	}
	
	public void deleteJob(int id) {
		for (Schedule s : scheduleService.getScheduleByJobId(id)) {
			scheduleService.deleteById(s);
		}
		jobsRepository.deleteJob(id);
	}
	
	public void isPaid(int id, Boolean bool) {
		jobsRepository.isPaid(id, bool);
	}

	public double findMaterialCostById(int id) {
		return jobsRepository.findMaterialCostById(id);
	}

	public void updateMaterialCost(int id, double materialCost) {
		jobsRepository.updateMaterialCost(id, materialCost);
	}

	/*
	* Update labor cost
	*/
	public void updateLaborCost(int jobId, double laborAmount) {
		jobsRepository.updateLaborCost(jobId, jobsRepository.findLaborCostById(jobId) + laborAmount);
	}

	public void checkIfPaid(int id, double totalPaid) {
		if (findTotalAmountChargedById(id) == totalPaid) {
			isPaid(id, true);
		}
	}
	
	public void addPayment(String customerName, double amountPaid) {
		PaymentLogic pl = new PaymentLogic();
		int id = findIdByCustomerName(customerName);
		double totalAmountPaid = findAmountPaidById(id) + amountPaid;
		pl.makePayment(findBalanceDueById(id), amountPaid);
		double newAmountDue = pl.getBalanceDue();
		updateAmountDueById(id, totalAmountPaid, newAmountDue);
		checkIfPaid(id, totalAmountPaid);
		System.out.println(totalAmountPaid);
	}
	
	public Jobs saveJobs(Jobs jobs) {
		return jobsRepository.save(jobs);
	}
	
}
