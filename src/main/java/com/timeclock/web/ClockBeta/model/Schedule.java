package com.timeclock.web.ClockBeta.model;

import javax.persistence.*;

/*
*
* Which employees are on which jobs.
*
*/

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

}
