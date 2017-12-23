/**
 * 
 */
package com.timeclock.web.ClockBeta.logistics;

import java.util.Date;

/**
 * @author Jacob Stewart
 * 
 * Calculates employee hours when clocking in and out.
 *
 */
public class ClockLogic {

	private Date startTime = new Date();
	private Date endTime;
	private long shiftTime;
	private long weeklyTime;
	private double weeklyHours;
	
	public ClockLogic() {}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(long shirfTime) {
		this.shiftTime = shirfTime;
	}

	public long getWeeklyTime() {
		return weeklyTime;
	}

	public void setWeeklyTime(long weeklyTime) {
		this.weeklyTime = weeklyTime;
	}
	
	public double getWeeklyHours() {
		return weeklyHours;
	}
	
	public void setWeeklyHours(double weeklyHours) {
		this.weeklyHours = weeklyHours;
	}
	
	private long calcShiftTime(Date start, Date end) {
		long startTime = start.getTime();
		long endTime = end.getTime();
		long shiftTime = endTime - startTime;
		setShiftTime(shiftTime);
		System.out.println(shiftTime + " Shift");
		return shiftTime;
	}
	
	public long calcWeeklyTime(long currentWeek, long shift) {
		long sum = 0;
		long toAdd = sum + shift;
		toAdd += currentWeek;
		setWeeklyTime(toAdd);
		System.out.println(weeklyTime + " week");
		return weeklyTime;
	}
	
	public void endShift(Date start, Date end) {
		calcShiftTime(start, end);
	}
	
	public double timeToHours(long time) {
		double timeAsDouble = time;
		double timeInHours = timeAsDouble / 3600000;
		double finalTime = (double)Math.round(timeInHours * 100d) / 100d;
		return finalTime;
	}
	
	public double longToDoubleInHours(long aLong) {
		double longToDouble = aLong;
		return longToDouble / 3600000;
	}
	
	public double calculatePay(double time, double payRate) {
		return (double)Math.round((time * payRate) * 100d) / 100d;
	}

}
