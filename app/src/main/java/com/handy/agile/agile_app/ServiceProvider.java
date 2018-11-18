package com.handy.agile.agile_app;


import com.handy.agile.agile_app.User;

import java.util.ArrayList;
import java.util.List;

public class ServiceProvider extends User {
    private List<DayEntry> daysOfWeek;
//    private DayEntry monday;
//    private DayEntry tuesday;
//    private DayEntry wednesday;
//    private DayEntry thursday;
//    private DayEntry friday;
//    private DayEntry saturday;
//    private DayEntry sunday;

    public ServiceProvider(String name, String lastName, String email, String password, String phoneNumber, String address, String role, String id){
        super(name, lastName, email, password, phoneNumber, address, role,id);

        daysOfWeek = new ArrayList<>();

        daysOfWeek.add(new DayEntry(false, "monday"));
        daysOfWeek.add(new DayEntry(false, "tuesday"));
        daysOfWeek.add(new DayEntry(false, "wednesday"));
        daysOfWeek.add(new DayEntry(false, "thursday"));
        daysOfWeek.add(new DayEntry(false, "friday"));
        daysOfWeek.add(new DayEntry(false, "saturday"));
        daysOfWeek.add(new DayEntry(false, "sunday"));



    }



    public ServiceProvider(){}

    public DayEntry getDay(int index) {
        return daysOfWeek.get(index);
    }

    public List<DayEntry> getDaysOfWeek() {
        return daysOfWeek;
    }

    //
//    public DayEntry getMonday() { return monday;}
//
//    public DayEntry getTuesday() { return tuesday;}
//
//    public DayEntry getWednesday() { return wednesday;}
//
//    public DayEntry getThursday() { return thursday;}
//
//    public DayEntry getFriday() { return friday;}
//
//    public DayEntry getSaturday() { return saturday;}
//
//    public DayEntry getSunday() { return sunday;}


}
