package com.handy.agile.agile_app;

import java.io.Serializable;

public class DayEntry implements Serializable {

    private boolean isOpen;
    private String day;
    private String startTime;
    private String endTime;

    public DayEntry(boolean isOpen, String day) {
        this.isOpen = isOpen;
        this.day = day;
    }

    public DayEntry() {}

    public void setOpen(boolean isOpen) { this.isOpen = isOpen;}

    public boolean isOpen() { return isOpen;}

    public void setDay(String day) { this.day = day; }

    public String getDay() { return day;}

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getStartTime() { return startTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getEndTime() { return endTime; }

}
