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
    @Column(name = "biz_id")
    private int bizId;
    @Column(name = "user")
	private String user;
    @Column(name = "clock_in")
	private Date clockIn;
    @Column(name = "clock_out")
	private Date clockOut;
    @Column(name = "shift_time")
	private long shiftTime; 
    @Column(name = "week_time")
	private long weekTime;
    @Column(name = "is_clocked")
    private Boolean clocked;
	
    public History() {
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getClockIn() {
		return clockIn;
	}

	public void setClockIn(Date clockIn) {
		this.clockIn = clockIn;
	}

	public Date getClockOut() {
		return clockOut;
	}

	public void setClockOut(Date clockOut) {
		this.clockOut = clockOut;
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

	public Boolean getClocked() {
		return clocked;
	}

	public void setClocked(Boolean clocked) {
		this.clocked = clocked;
	}
    
    
}
