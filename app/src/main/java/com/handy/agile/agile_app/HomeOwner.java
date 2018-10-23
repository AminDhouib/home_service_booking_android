package com.handy.agile.agile_app;



public class HomeOwner extends User {

    //TODO: user superclass already has these string covered?
    private String name;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private String role;

    private String id;

    public HomeOwner(String name, String lastName,String email, String password, String phoneNumber, String address, String role, String id){
        super(name, lastName, email, password, phoneNumber, address, role, id);

    }



    public HomeOwner(){}
}
