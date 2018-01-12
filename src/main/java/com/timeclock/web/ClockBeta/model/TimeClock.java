package com.timeclock.web.ClockBeta.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TimeClock {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name = "business_id")
    private int businessId;
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "shift_start")
    private Date shiftStart;
    @Column(name = "shift_end")
    private Date shiftEnd;
    @Column(name = "pay_period_time")
    private long payPeriodTime;
    @Column(name = "hours_worked")
    private double hoursWorked;
    @Column(name = "last_refresh")
    private Date lastRefresh;
    @Column(name = "is_clocked")
    private boolean isClocked;

    public TimeClock() {
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

    public Date getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(Date shiftStart) {
        this.shiftStart = shiftStart;
    }

    public Date getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(Date shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public long getPayPeriodTime() {
        return payPeriodTime;
    }

    public void setPayPeriodTime(long payPeriodTime) {
        this.payPeriodTime = payPeriodTime;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public boolean isClocked() {
        return isClocked;
    }

    public void setClocked(boolean clocked) {
        isClocked = clocked;
    }
}
