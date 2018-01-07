package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Iterable<Schedule> findScheduleByBizId(int bizId);

    Iterable<Schedule> findScheduleByJobId(int jobId);

    Iterable<Schedule> findScheduleByClockId(int clockId);

    Iterable<Schedule> findScheduleByClockIdAndJobId(int clockId, int jobId);
//
//    void deleteSchedule(Schedule schedule);

    boolean existsByClockIdAndJobId(int clockId, int jobId);

    @Query("SELECT jobId FROM com.timeclock.web.ClockBeta.model.Schedule"
            + " WHERE clockId= :clockId")
    Iterable<Integer> findJobIdsByClockId(@Param("clockId")int clockId);

    @Query("SELECT clockId FROM com.timeclock.web.ClockBeta.model.Schedule"
            + " WHERE jobId= :jobId")
    Iterable<Integer> findClockIdsByJobId(@Param("jobId")int jobId);

}
