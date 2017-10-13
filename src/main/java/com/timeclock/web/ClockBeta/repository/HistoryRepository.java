package com.timeclock.web.ClockBeta.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;

import com.timeclock.web.ClockBeta.model.History;

public interface HistoryRepository extends CrudRepository <History, Long> {
	
	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.History SET clock_in=:startTime, clocked=true WHERE id=:id")
	void updateClock(@Param("id")int id, 
			  @Param("startTime")Date startTime); 

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.History SET clock_in=:startTime, clock_out=:endTime, " +
			"shift_time=:shiftTime, week_time=:weeklyTime, clocked=false WHERE userId=:userId")
	void updateClock(@Param("userId")int userId,
			  @Param("startTime")Date startTime,
			  @Param("endTime")Date endTime, 
			  @Param("shiftTime")long shiftTime, 
			  @Param("weeklyTime")long weeklyTime);

//  Since you can't use insert statements using CRUDrepository, I'll just have to use a method with setters...
//	@Modifying
//	@Transactional
//	@Query("INSERT INTO com.timeclock.web.ClockBeta.model.History (clock_in, clock_out, " +
//			"shift_time, week_time, clocked, user_id) " +
//			"SELECT clock_in, clock_out, shift_time, week_time, clocked, id " +
//			"FROM com.timeclock.web.ClockBeta.model.Clock")
//	void saveHistory();

	
}
