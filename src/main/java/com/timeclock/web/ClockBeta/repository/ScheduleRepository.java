package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Iterable<Schedule> findScheduleByBusinessId(int businessId);

    Iterable<Schedule> findScheduleByJobId(int jobId);

    Iterable<Schedule> findScheduleByEmployeeId(int employeeId);

    Iterable<Schedule> findScheduleByEmployeeIdAndJobId(int clockId, int jobId);

    boolean existsByEmployeeIdAndJobId(int employeeId, int jobId);

    @Query("SELECT jobId FROM com.timeclock.web.ClockBeta.model.Schedule"
            + " WHERE employeeId= :employeeId")
    Iterable<Integer> findJobIdsByClockId(@Param("employeeId")int employeeId);

    @Query("SELECT employeeId FROM com.timeclock.web.ClockBeta.model.Schedule"
            + " WHERE jobId= :jobId")
    Iterable<Integer> findEmployeeIdsByJobId(@Param("jobId")int jobId);

}
