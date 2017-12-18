package com.timeclock.web.ClockBeta.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name = "biz_id")
    private int bizId;
    @Column(name = "clock_id")
    private int clockId;
    @Column(name = "job_id")
    private int jobId;
    @Column(name = "day")
    private LocalDate day;
    @Column(name = "time")
    private Date time;
    @Column(name = "present")
    private Boolean present = false;

    public Schedule() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public int getClockId() {
        return clockId;
    }

    public void setClockId(int clockId) {
        this.clockId = clockId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
