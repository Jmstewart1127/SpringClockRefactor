package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Iterable<Schedule> findScheduleByBizId(int bizId);

    Iterable<Schedule> findScheduleByClockId(int clockId);

    @Query("SELECT jobId FROM com.timeclock.web.ClockBeta.model.Schedule"
            + " WHERE clockId= :clockId")
    Iterable<Integer> findJobIdsByClockId(@Param("clockId")int clockId);

    @Query("SELECT id FROM com.timeclock.web.ClockBeta.model.Schedule"
            + " WHERE clockId= :clockId AND jobId= :jobId")
    int checkIfScheduleExists(
            @Param("clockId")int clockId,
            @Param("jobId")int jobId);

}
