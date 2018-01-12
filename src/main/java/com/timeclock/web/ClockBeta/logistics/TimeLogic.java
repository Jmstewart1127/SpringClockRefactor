/**
 * 
 */
package com.timeclock.web.ClockBeta.logistics;

import java.util.Date;

/**
 * @author Jacob Stewart
 * 
 * Calculates employee hours and pay when clocking in and out.
 *
 */
public class TimeLogic {

	private long shiftTime;
	private long weeklyTime;
	
	public TimeLogic() {}

	public long getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(long shiftTime) {
		this.shiftTime = shiftTime;
	}

	public long getWeeklyTime() {
		return weeklyTime;
	}

	public void setWeeklyTime(long weeklyTime) {
		this.weeklyTime = weeklyTime;
	}
	
	public void calcWeeklyTime(long currentWeek, long shiftTime) {
		shiftTime += currentWeek;
		setWeeklyTime(shiftTime);
	}
	
	public void calcShiftTime(Date start, Date end) {
		setShiftTime(end.getTime() - start.getTime());
	}
	
	public double timeToHours(long time) {
		return (double)Math.round(((double)time / 3600000) * 100d) / 100d;
	}
	
	public double longToDoubleInHours(long time) {
		return (double)time / 3600000;
	}
	
	public double calculatePay(double time, double payRate) {
		return (double)Math.round((time * payRate) * 100d) / 100d;
	}

}
