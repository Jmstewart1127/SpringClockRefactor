package com.timeclock.web.ClockBeta.repository;

import java.util.Date;

import com.timeclock.web.ClockBeta.model.Business;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.timeclock.web.ClockBeta.model.Clock;

public interface ClockRepository extends CrudRepository <Clock, Long> {
	
	Iterable<Clock> findById(int id);
	
	Clock findByUser(String user);
	
	Clock findByClocked(Boolean clocked);
	
	Iterable<Clock> findByBizId(int bizId);

	@Query("SELECT bizId FROM com.timeclock.web.ClockBeta.model.Clock WHERE id= :id")
	int findBizIdById(@Param("id")int id);

	@Query("SELECT clocked FROM com.timeclock.web.ClockBeta.model.Clock WHERE id= :id")
	Boolean findClockedById(@Param("id")int id);
	
	@Query("SELECT payRate FROM com.timeclock.web.ClockBeta.model.Clock WHERE id= :id")
	double findPayRateById(@Param("id")int id);
	
	@Query("SELECT clockIn FROM com.timeclock.web.ClockBeta.model.Clock WHERE id= :id")
	Date findStartTimeById(@Param("id")int id);
	
	@Query("SELECT weekTime FROM com.timeclock.web.ClockBeta.model.Clock WHERE id= :id")
	Long findWeekTimeById(@Param("id")int id);

	@Query("SELECT lastRefresh FROM com.timeclock.web.ClockBeta.model.Clock WHERE id= :id")
	Date findlastRefreshById(@Param("id")int id);
	
	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Clock "
			+ "SET clock_in=:startTime, last_refresh=:lastRefresh, clocked=true WHERE id=:id")
	void updateClock(@Param("id")int id,
					 @Param("startTime")Date startTime,
					 @Param("lastRefresh")Date lastRefresh);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Clock SET "
			+ "clock_out=:endTime, shift_time=:shiftTime, week_time=:weeklyTime, "
			+ "week_time_in_hours=:weeklyTimeInHours, total_pay=:totalPay, clocked=false WHERE id=:id")
	void updateClock(@Param("id")int id, 
			  @Param("endTime")Date endTime, 
			  @Param("shiftTime")long shiftTime, 
			  @Param("weeklyTime")long weeklyTime,
			  @Param("weeklyTimeInHours")double weeklyTimeInHours,
			  @Param("totalPay")double totalPay);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Clock SET "
			+ "shift_time=:shiftTime, week_time=:weeklyTime, "
			+ "week_time_in_hours=:weeklyTimeInHours, total_pay=:totalPay, "
			+ "last_refresh=:lastRefresh, clocked=true WHERE id=:id")
	void refreshClock(@Param("id")int id,
					 @Param("shiftTime")long shiftTime,
					 @Param("weeklyTime")long weeklyTime,
					 @Param("weeklyTimeInHours")double weeklyTimeInHours,
					 @Param("totalPay")double totalPay,
					 @Param("lastRefresh")Date lastRefresh);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Clock SET "
			+ "clock_out=null, clock_in=null, shift_time=0, week_time=0, "
			+ "week_time_in_hours=0, total_pay=0, clocked=false WHERE bizId=:bizId")
	void resetClock(@Param("bizId")int bizId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM com.timeclock.web.ClockBeta.model.Clock WHERE id=:id")
	void deleteClock(@Param("id")int id);
	  
}
