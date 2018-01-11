package com.timeclock.web.ClockBeta.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class History {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name = "employee_id")
	private int employeeId;
    @Column(name = "business_id")
    private int businessId;
    @Column(name = "employeeName")
	private String employeeName;
    @Column(name = "clock_in_time")
	private Date clockInTime;
    @Column(name = "clock_out_time")
	private Date clockOutTime;
    @Column(name = "shift_time")
	private long shiftTime; 
    @Column(name = "week_time")
	private long weekTime;
    @Column(name = "is_clocked")
    private Boolean isClockedIn;
	
    public History() {
    	super();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
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

	public Boolean getIsClockedIn() {
		return isClockedIn;
	}

	public void setIsClockedIn(Boolean isClockedIn) {
		this.isClockedIn = isClockedIn;
	}
    
    
}
