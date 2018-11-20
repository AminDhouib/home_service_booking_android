package com.handy.agile.agile_app;

import java.io.Serializable;
import java.util.List;


public class User implements Serializable {

    protected String name;

    protected String lastName;

    protected String email;

    protected String password;

    protected String phoneNumber;

    protected String address;

    protected String role;

    protected String id;

    private List<DayEntry> daysOfWeek;
    private String companyName;
    private String description;
    private String licensed;


    public User(String name, String lastName,String email, String password, String phoneNumber, String address, String role, String id){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.id = id;

    }



    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getId() { return  id; }

    public void setId(String id) {
        this.id = id;
    }

    public List<DayEntry> getDaysOfWeek() {
        return daysOfWeek;
    }
    public void setDaysOfWeek(List<DayEntry> daysOfWeek) { this.daysOfWeek = daysOfWeek;}

    public String getCompanyName() { return companyName;}
    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public String getLicensed() { return licensed;}
    public void  setLicensed(String licensed) { this.licensed = licensed;}

    public String getDescription() {return description;}
    public void setDescription(String description) { this.description = description;}



}
