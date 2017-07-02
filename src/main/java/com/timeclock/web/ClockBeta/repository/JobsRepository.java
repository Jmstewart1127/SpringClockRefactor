package com.timeclock.web.ClockBeta.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.timeclock.web.ClockBeta.model.Jobs;

public interface JobsRepository extends CrudRepository <Jobs, Long> {
	
	Jobs findById(int id);
	
	Jobs findByCategory(String category);
	
	Jobs findByCustomerName(String customerName);
	
	Jobs deleteById(int id);
	
	@Query("SELECT amountCharged FROM com.timeclock.web.ClockBeta.model.Jobs"
			+ " WHERE id= :id")
	double findAmountChargedById(@Param("id")int id);
	
	@Query("SELECT amountDue FROM com.timeclock.web.ClockBeta.model.Jobs"
			+ " WHERE id= :id")
	double findAmountDueById(@Param("id")int id);
	
	@Query("SELECT amountPaid FROM com.timeclock.web.ClockBeta.model.Jobs"
			+ " WHERE id= :id")
	double findAmountPaidById(@Param("id")int id);
	
	@Query("SELECT id FROM com.timeclock.web.ClockBeta.model.Jobs"
			+ " WHERE customer_name= :customerName")
	int findIdByCustomerName(@Param("customerName")String customerName );
	
	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Jobs "
			+ "SET is_paid =:isPaid WHERE id=:id")
	void isPaid(@Param("id")int id,
				@Param("isPaid") Boolean isPaid);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Jobs "
			+ "SET amount_due=:amountDue, amount_paid=:amountPaid WHERE id=:id")
	void updateAmountDue(@Param("id")int id,
			  @Param("amountPaid")double amountPaid,
			  @Param("amountDue")double amountDue); 
	
	@Modifying
	@Transactional
	@Query("DELETE FROM com.timeclock.web.ClockBeta.model.Jobs WHERE id=:id")
	void deleteJob(@Param("id")int id);
	
	
}
