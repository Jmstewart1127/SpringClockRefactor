package com.timeclock.web.ClockBeta.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    @Column(name = "business_id")
    private int businessId;
    @Column(name = "clocked_in_at")
	private int jobIdClockedInAt;
    @Column(name = "employee_name")
	private String employeeName;
    @Column(name = "clock_in_time")
	private Date clockInTime;
    @Column(name = "clock_out_time")
	private Date clockOutTime;
    @Column(name = "shift_time")
	private long shiftTime; 
    @Column(name = "week_time")
	private long weekTime;
    @Column(name = "week_time_in_hours")
    private double weekTimeInHours;
    @Column(name = "is_clocked_in")
    private Boolean isClockedIn = false;
    @Column(name = "pay_rate")
    private double payRate;
    @Column(name = "total_pay")
    private double totalPay;
	@Column(name = "last_refresh")
	private Date lastRefresh;


	public Employee() {
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

	public int getjobIdClockedInAt() {
		return jobIdClockedInAt;
	}

	public void setJobIdClockedInAt(int jobIdClockedInAt) {
		this.jobIdClockedInAt = jobIdClockedInAt;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(Date clockInTime) {
		this.clockInTime = clockInTime;
	}

	public Date getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(Date clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public long getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(long shiftTime) {
		this.shiftTime = shiftTime;
	}

	public long getWeekTime() {
		return weekTime;
	}

	public void setWeekTime(long weekTime) {
		this.weekTime = weekTime;
	}

	public double getWeekTimeInHours() {
		return weekTimeInHours;
	}

	public void setWeekTimeInHours(double weekTimeInHours) {
		this.weekTimeInHours = weekTimeInHours;
	}

	public Boolean getIsClockedIn() {
		return isClockedIn;
	}

	public void setIsClockedIn(Boolean isClockedIn) {
		this.isClockedIn = isClockedIn;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}

	public Date getLastRefresh() {
		return lastRefresh;
	}

	public void setLastRefresh(Date lastRefresh) {
		this.lastRefresh = lastRefresh;
	}
}
