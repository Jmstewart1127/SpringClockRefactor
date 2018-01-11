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

	Iterable<History> findByEmployeeId(int id);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.History SET clock_in_time=:clockInTime, " +
			"isClockedIn=true WHERE id=:id")
	void updateEmployeeHistory(@Param("id")int id,
							   @Param("clockInTime")Date clockInTime);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.History SET clock_in_time=:clockInTime, " +
			"clock_out_time=:clockOutTime, shift_time=:shiftTime, week_time=:weeklyTime, " +
			"isClockedIn=false WHERE employeeId=:employeeId")
	void updateEmployeeHistory(@Param("employeeId")int employeeId,
							   @Param("clockInTime")Date clockInTime,
							   @Param("clockOutTime")Date clockOutTime,
							   @Param("shiftTime")long shiftTime,
							   @Param("weeklyTime")long weeklyTime);

	
}
