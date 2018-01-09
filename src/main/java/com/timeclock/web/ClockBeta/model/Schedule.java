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
    @Column(name = "business_id")
    private int businessId;
    @Column(name = "employee_id")
    private int employeeId;
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

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

}
