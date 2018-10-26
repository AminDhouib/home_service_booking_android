# AGILE
Group Project For SEG 2105.

Group members:

-Yesmine Zribi
-Amin Dhouib
-Gregory Ivo
-Jared Lueck

Purpose of the Project: This application allows home owners to connect with service providers.

****************************************************************************************

Features which have currently been developed include: 

(1) Account creation: 
-One and only one Admin account can be created. 
-Home owners and service providers can create many accounts.
-Once the signup is successful users are taken back to the login screen
-Accounts are saved in a cloud based storage (Firebase)

(2) Authentication during account creation: 
-Every sign up field (name, last name, email, etc.) is validated. 
-Can validate if the entered password is the same as the confirmed password.
-One cannot create an account with a password that has less than 8 characters. 

(3) Login attempt:
-Users cannot log in if they do not have an account already. 

(4) Login:
-Users will see a welcome message with their name and their role. 
-Admin can log in and see a welcome message and a list of all users in the database.
-Home owners and service providers can only see a welcome message (for now).

****************************************************************************************

We will the following features to our android application in our next revision:

1 - Allow users to search for service providers

2 - Users will be able to rate a service and to book one as well

3 - Service providers will add his availabilities. 

4 - Allow Admin to delete, remove or add users
