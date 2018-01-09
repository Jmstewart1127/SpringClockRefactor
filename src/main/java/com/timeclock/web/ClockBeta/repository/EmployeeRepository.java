package com.timeclock.web.ClockBeta.repository;

import java.util.Date;

import com.timeclock.web.ClockBeta.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends CrudRepository <Employee, Long> {
	
	Iterable<Employee> findById(int id);
	
	Employee findUserById(int id);
	
	Employee findByEmployeeName(String employeeName);
	
	Employee findByIsClockedIn(Boolean clocked);

	Iterable<Employee> findByBusinessId(int businessId);

	@Query("SELECT businessId FROM com.timeclock.web.ClockBeta.model.Employee WHERE id= :id")
	int findBusinessIdById(@Param("id")int id);

	@Query("SELECT jobIdClockedInAt FROM com.timeclock.web.ClockBeta.model.Employee WHERE id= :id")
	int findJobIdClockedInAtById(@Param("id")int id);

	@Query("SELECT isClockedIn FROM com.timeclock.web.ClockBeta.model.Employee WHERE id= :id")
	Boolean findIsClockedInById(@Param("id")int id);
	
	@Query("SELECT payRate FROM com.timeclock.web.ClockBeta.model.Employee WHERE id= :id")
	double findPayRateById(@Param("id")int id);
	
	@Query("SELECT clockInTime FROM com.timeclock.web.ClockBeta.model.Employee WHERE id= :id")
	Date findStartTimeById(@Param("id")int id);
	
	@Query("SELECT weekTime FROM com.timeclock.web.ClockBeta.model.Employee WHERE id= :id")
	Long findWeekTimeById(@Param("id")int id);

	@Query("SELECT lastRefresh FROM com.timeclock.web.ClockBeta.model.Employee WHERE id= :id")
	Date findLastRefreshById(@Param("id")int id);
	
	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Employee "
			+ "SET clock_in=:startTime, last_refresh=:lastRefresh, is_clocked_in=true WHERE id=:id")
	void updateClock(
			@Param("id")int id,
			@Param("startTime")Date startTime,
			@Param("lastRefresh")Date lastRefresh);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Employee SET "
			+ "clock_out=:endTime, shift_time=:shiftTime, week_time=:weeklyTime, "
			+ "week_time_in_hours=:weeklyTimeInHours, total_pay=:totalPay, clocked=false WHERE id=:id")
	void updateClock(
			@Param("id")int id,
			@Param("endTime")Date endTime,
			@Param("shiftTime")long shiftTime,
			@Param("weeklyTime")long weeklyTime,
			@Param("weeklyTimeInHours")double weeklyTimeInHours,
			@Param("totalPay")double totalPay);

	/*
	* Employee In With Job ID
	*/
	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Employee "
			+ "SET clock_in=:startTime, clocked_in_at=:clockedInAt, last_refresh=:lastRefresh, " +
			"clocked=true WHERE id=:id")
	void clockIn(
			@Param("id")int id,
			@Param("clockedInAt")int clockedInAt,
			@Param("startTime")Date startTime,
			@Param("lastRefresh")Date lastRefresh);

	/*
	* Employee Out With Job ID
	*/
	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Employee SET "
			+ "clocked_in_at=:clockedInAt, clock_out=:endTime, shift_time=:shiftTime, week_time=:weeklyTime, "
			+ "week_time_in_hours=:weeklyTimeInHours, total_pay=:totalPay, clocked=false WHERE id=:id")
	void clockOut(
			@Param("id")int id,
			@Param("clockedInAt")int clockedInAt,
			@Param("endTime")Date endTime,
			@Param("shiftTime")long shiftTime,
			@Param("weeklyTime")long weeklyTime,
			@Param("weeklyTimeInHours")double weeklyTimeInHours,
			@Param("totalPay")double totalPay);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Employee SET "
			+ "shift_time=:shiftTime, week_time=:weeklyTime, clocked_in_at=:clockedInAt,"
			+ "week_time_in_hours=:weeklyTimeInHours, total_pay=:totalPay, "
			+ "last_refresh=:lastRefresh, clocked=true WHERE id=:id")
	void refreshClockWithJobId(
			@Param("id")int id,
			@Param("shiftTime")long shiftTime,
			@Param("weeklyTime")long weeklyTime,
			@Param("clockedInAt")int clockedInAt,
			@Param("weeklyTimeInHours")double weeklyTimeInHours,
			@Param("totalPay")double totalPay,
			@Param("lastRefresh")Date lastRefresh);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Employee SET "
			+ "shift_time=:shiftTime, week_time=:weeklyTime, "
			+ "week_time_in_hours=:weeklyTimeInHours, total_pay=:totalPay, "
			+ "last_refresh=:lastRefresh, clocked=true WHERE id=:id")
	void refreshClock(
			@Param("id")int id,
			@Param("shiftTime")long shiftTime,
			@Param("weeklyTime")long weeklyTime,
			@Param("weeklyTimeInHours")double weeklyTimeInHours,
			@Param("totalPay")double totalPay,
			@Param("lastRefresh")Date lastRefresh);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Employee SET "
			+ "clock_out=null, clock_in=null, shift_time=0, week_time=0, "
			+ "week_time_in_hours=0, total_pay=0, clocked=false WHERE bizId=:bizId")
	void resetClock(@Param("bizId")int bizId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM com.timeclock.web.ClockBeta.model.Employee WHERE id=:id")
	void deleteClock(@Param("id")int id);
	  
}
