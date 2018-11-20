package com.handy.agile.agile_app;


import com.handy.agile.agile_app.User;

import java.util.ArrayList;
import java.util.List;

public class ServiceProvider extends User {
    private List<DayEntry> daysOfWeek;
    private String companyName;
    private String description;
    private boolean licensed;

    public ServiceProvider(String name, String lastName, String email, String password, String phoneNumber, String address, String role, String id){
        super(name, lastName, email, password, phoneNumber, address, role,id);

        daysOfWeek = new ArrayList<>();

        daysOfWeek.add(new DayEntry(false, "Monday",0));
        daysOfWeek.add(new DayEntry(false, "Tuesday",1));
        daysOfWeek.add(new DayEntry(false, "Wednesday",2));
        daysOfWeek.add(new DayEntry(false, "Thursday",3));
        daysOfWeek.add(new DayEntry(false, "Friday",4));
        daysOfWeek.add(new DayEntry(false, "Saturday",5));
        daysOfWeek.add(new DayEntry(false, "Sunday",6));
        companyName = "Not yet specified";
        description = "Enter description";



    }

    public ServiceProvider(User user) {
        super(user.name, user.lastName, user.email, user.password, user.phoneNumber, user.address, user.role, user.id);

        daysOfWeek = new ArrayList<>();

        daysOfWeek.add(new DayEntry(false, "Monday",0));
        daysOfWeek.add(new DayEntry(false, "Tuesday",1));
        daysOfWeek.add(new DayEntry(false, "Wednesday",2));
        daysOfWeek.add(new DayEntry(false, "Thursday",3));
        daysOfWeek.add(new DayEntry(false, "Friday",4));
        daysOfWeek.add(new DayEntry(false, "Saturday",5));
        daysOfWeek.add(new DayEntry(false, "Sunday",6));
        companyName = "Not yet specified";
        description = "Enter description";

    }



    public ServiceProvider(){
        daysOfWeek = new ArrayList<>();

        daysOfWeek.add(new DayEntry(false, "Monday",0));
        daysOfWeek.add(new DayEntry(false, "Tuesday",1));
        daysOfWeek.add(new DayEntry(false, "Wednesday",2));
        daysOfWeek.add(new DayEntry(false, "Thursday",3));
        daysOfWeek.add(new DayEntry(false, "Friday",4));
        daysOfWeek.add(new DayEntry(false, "Saturday",5));
        daysOfWeek.add(new DayEntry(false, "Sunday",6));
        companyName = "Not yet specified";
        description = "Enter description";
    }


    public List<DayEntry> getDaysOfWeek() {
        return daysOfWeek;
    }
    public void setDaysOfWeek(List<DayEntry> daysOfWeek) { this.daysOfWeek = daysOfWeek;}

    public String getCompanyName() { return companyName;}
    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public boolean getLicensed() { return licensed;}
    public void setLicensed(boolean licensed) { this.licensed = licensed;}

    public String getDescription() {return description;}
    public void setDescription(String description) { this.description = description;}


}
