package com.handy.agile.agile_app.UtilityClasses;
import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;

public class Appointment {

    private User homeOwner;
    private ServiceProvider serviceProvider;
    private String time;
    private String day;
    private Service service;

    public Appointment(){}

    public Appointment(User homeOwner, ServiceProvider serviceProvider, String time, String day, Service service){
        this.homeOwner = homeOwner;
        this.serviceProvider = serviceProvider;
        this.time = time;
        this.day = day;
        this.service = service;
    }

    public User getHomeOwner() {
        return homeOwner;
    }

    public void setHomeOwner(User homeOwner) {
        this.homeOwner = homeOwner;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
