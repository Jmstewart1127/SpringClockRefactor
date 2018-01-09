package com.timeclock.web.ClockBeta.logistics;

import java.util.Date;

public class TimeClock {

    private Date shiftStart;
    private Date shiftEnd;

    public TimeClock() {
        super();
    }

    public void startShift() {
        Date startTime = new Date();
        setShiftStart(startTime);
    }

    public void endShift() {
        Date endTime = new Date();
        setShiftEnd(endTime);
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
}
