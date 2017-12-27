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

	private long shiftTime;
	private long weeklyTime;
	
	public ClockLogic() {}

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
	
	private void calcShiftTime(Date start, Date end) {
		setShiftTime(end.getTime() - start.getTime());
	}
	
	public void calcWeeklyTime(long currentWeek, long shift) {
		shift += currentWeek;
		setWeeklyTime(shift);
	}
	
	public void endShift(Date start, Date end) {
		calcShiftTime(start, end);
	}
	
	public double timeToHours(long time) {
		return (double)Math.round(((double)time / 3600000) * 100d) / 100d;
	}
	
	public double longToDoubleInHours(long aLong) {
		return (double)aLong / 3600000;
	}
	
	public double calculatePay(double time, double payRate) {
		return (double)Math.round((time * payRate) * 100d) / 100d;
	}

}
