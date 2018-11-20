package com.handy.agile.agile_app.UtilityClasses;

import java.io.Serializable;

public class DayEntry implements Serializable {

    private int id;
    private boolean isOpen;
    private String day;
    private String startTime;
    private String endTime;

    public DayEntry(boolean isOpen, String day,int id) {
        this.id = id;
        this.isOpen = isOpen;
        this.day = day;
    }


    public DayEntry() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
