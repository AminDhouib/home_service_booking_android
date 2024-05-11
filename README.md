Build Status
[![Build Status](https://circleci.com/gh/AminDhouib/AGILE.png?branch=master)](https://circleci.com/gh/AminDhouib/AGILE)


# Home Service Booking Tool For Android

This application allows home owners to connect with service providers.

## Setup Guide

(1) Install build:
-On your Android phone, enable app installation from unknown source (temporarily)
-Connect Android to a laptop or PC
-Download AGILE.apk, right-click the build file > send to > locate your Android phone
-On your Android, navigate to My Files > Installation files > Install the APK

(2) Install from source:
-Download source code from GitHub
-Import the project to Android Studio
-Run the Main Activity


## Features

Features that have currently been developed include: 

(1) Account creation: 
-One and only one Admin account can be created. 
-Homeowners and service providers can create many accounts.
-Once the signup is successful, users are taken back to the login screen
-Accounts are saved in cloud-based storage (Firebase)

(2) Authentication during account creation: 
-Every signup field (name, last name, email, etc.) is validated. 
-Can validate if the entered password is the same as the confirmed password.
-One can only create an account with a password of at least eight characters. 

(3) Login attempt:
-Users can only log in if they already have an account. 

(4) Login:
-Users will see a welcome message with their name and their role. 
-Admin can log in and see a welcome message and a list of all users in the database.
-Homeowners and service providers can only see a welcome message (for now).

(5) Others:
- Admin can create, edit, and delete services. 
- A service has a name and an hourly rate. 
- A service provider can edit his/her account. He/she has to include their phone number, address, name, last name, and their company name. The description is optional. 
- A service provider may not edit their email. 
- A service provider can enter and edit his/her availability. 
- A service provider can search services added by the admin and add them to his/her account. 
- A service provider may delete services from his/her account. 
- A service provider cannot edit the service name or the rate of the service as it is governed by the admin. 


