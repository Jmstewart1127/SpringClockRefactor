package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.TimeClock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface TimeClockRepository extends CrudRepository<TimeClock, Long> {

    TimeClock findByEmployeeId(int id);

    Iterable<TimeClock> findByBusinessId(int businessId);

    @Modifying
    @Transactional
    @Query("UPDATE com.timeclock.web.ClockBeta.model.TimeClock SET "
            + "week_time=:weeklyTime, clocked_in_at=:clockedInAt,"
            + "week_time_in_hours=:weeklyTimeInHours, total_pay=:totalPay, "
            + "last_refresh=:lastRefresh, is_clocked_in=true WHERE id=:id")
    void refreshClockWithJobId(
            @Param("id")int id,
            @Param("weeklyTime")long weeklyTime,
            @Param("weeklyTimeInHours")double weekTimeInHours,
            @Param("totalPay")double totalPay,
            @Param("lastRefresh")Date lastRefresh);

    @Modifying
    @Transactional
    @Query("UPDATE com.timeclock.web.ClockBeta.model.TimeClock SET "
            + "week_time=:weeklyTime, hours_worked=:hours_worked, total_pay=:totalPay, "
            + "last_refresh=:lastRefresh, is_clocked_in=true WHERE id=:id")
    void refreshClock(
            @Param("id")int id,
            @Param("payPeriodTime")long payPeriodTime,
            @Param("weeklyTimeInHours")double weeklyTimeInHours,
            @Param("totalPay")double totalPay,
            @Param("lastRefresh")Date lastRefresh);

    /*
    * Clock In
    */
    @Modifying
    @Transactional
    @Query("UPDATE com.timeclock.web.ClockBeta.model.TimeClock "
            + "SET shift_start=:shiftStart, last_refresh=:lastRefresh, is_clocked_in=true WHERE id=:id")
    void clockIn(
            @Param("id")int id,
            @Param("shiftStart")Date shiftStart,
            @Param("lastRefresh")Date lastRefresh);

    /*
    * Clock Out
    */
    @Modifying
    @Transactional
    @Query("UPDATE com.timeclock.web.ClockBeta.model.TimeClock SET "
            + "shift_end=:shiftEnd, pay_period_time=:payPeriodTime, hours_worked=:hoursWorked,"
            + "is_clocked=false WHERE id=:id")
    void clockOut(
            @Param("id")int id,
            @Param("shiftEnd")Date shiftEnd,
            @Param("payPeriodTime")long payPeriodTime,
            @Param("hoursWorked")double hoursWorked);

    /*
    * Reset Pay Period
    */
    @Modifying
    @Transactional
    @Query("UPDATE com.timeclock.web.ClockBeta.model.TimeClock SET "
            + "shift_end=null, shift_start=null, pay_period_time=0, "
            + "hours_worked=0, last_refresh=null, is_clocked=false WHERE businessId=:businessId")
    void resetPayPeriod(@Param("businessId")int businessId);


}
