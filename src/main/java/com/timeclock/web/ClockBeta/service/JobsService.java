package com.timeclock.web.ClockBeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.logistics.PaymentLogic;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.repository.JobsRepository;

@Service
public class JobsService {

	@Autowired
	JobsRepository jobsRepository;
	
	public Jobs findById(int id) {
		return jobsRepository.findById(id);
	}
	
	public Iterable<Jobs> findAll() {
		return jobsRepository.findAll();
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
		jobsRepository.deleteJob(id);
	}
	
	public void isPaid(int id, Boolean bool) {
		jobsRepository.isPaid(id, bool);
	}
	
	public void checkIfPaid(int id, double totalPaid) {
		double totalAmountCharged = findTotalAmountChargedById(id);
		Boolean paid = true;
		
		if (totalAmountCharged == totalPaid) {
			isPaid(id, paid);
		}
	}
	
	public void addPayment(String customerName, double amountPaid) {
		PaymentLogic pl = new PaymentLogic();
		int id = findIdByCustomerName(customerName);
		double amountDue = findBalanceDueById(id);
		double totalAmountPaid = findAmountPaidById(id) + amountPaid;
		pl.makePayment(amountDue, amountPaid);
		double newAmountDue = pl.getBalanceDue();
		updateAmountDueById(id, totalAmountPaid, newAmountDue);
		checkIfPaid(id, totalAmountPaid);
		System.out.println(totalAmountPaid);
	}
	
	public Jobs saveJobs(Jobs jobs) {
		return jobsRepository.save(jobs);
	}
	
}
