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

        daysOfWeek.add(new DayEntry(false, "monday",0));
        daysOfWeek.add(new DayEntry(false, "tuesday",1));
        daysOfWeek.add(new DayEntry(false, "wednesday",2));
        daysOfWeek.add(new DayEntry(false, "thursday",3));
        daysOfWeek.add(new DayEntry(false, "friday",4));
        daysOfWeek.add(new DayEntry(false, "saturday",5));
        daysOfWeek.add(new DayEntry(false, "sunday",6));



    }



    public ServiceProvider(){
        daysOfWeek = new ArrayList<>();

        daysOfWeek.add(new DayEntry(false, "monday",0));
        daysOfWeek.add(new DayEntry(false, "tuesday",1));
        daysOfWeek.add(new DayEntry(false, "wednesday",2));
        daysOfWeek.add(new DayEntry(false, "thursday",3));
        daysOfWeek.add(new DayEntry(false, "friday",4));
        daysOfWeek.add(new DayEntry(false, "saturday",5));
        daysOfWeek.add(new DayEntry(false, "sunday",6));
    }

    public DayEntry getDay(int index) {
        return daysOfWeek.get(index);
    }

//    public int getIndexOfDay(String day) {
//       DayEntry d;
//       for (int i = 0; i < daysOfWeek.size(); i++) {
//           d = daysOfWeek.get(i);
//           if (d.getDay().equals(day)) {
//               return i;
//           }
//       }
//       return -1;
//    }

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
