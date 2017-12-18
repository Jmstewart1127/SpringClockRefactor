package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Iterable<Schedule> findScheduleByBizId(int bizId);

    Iterable<Schedule> findScheduleByClockId(int clockId);

}
