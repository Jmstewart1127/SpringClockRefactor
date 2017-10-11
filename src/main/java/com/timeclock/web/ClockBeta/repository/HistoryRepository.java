package com.timeclock.web.ClockBeta.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
	@Query("UPDATE com.timeclock.web.ClockBeta.model.History SET clock_out=:endTime, shift_time=:shiftTime, " +
			"week_time=:weeklyTime, clocked=false WHERE id=:id")
	void updateClock(@Param("id")int id, 
			  @Param("endTime")Date endTime, 
			  @Param("shiftTime")long shiftTime, 
			  @Param("weeklyTime")long weeklyTime);
	
}
