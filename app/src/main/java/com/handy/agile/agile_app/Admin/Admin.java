package com.handy.agile.agile_app.Admin;


import com.handy.agile.agile_app.User;

public class Admin extends User {
    Admin(String name, String lastName,String email, String password, String phoneNumber, String address, String role, String id){
        super(name, lastName, email, password, phoneNumber, address, role, id);
    }
}
