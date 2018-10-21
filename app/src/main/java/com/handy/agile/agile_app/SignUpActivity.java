package com.handy.agile.agile_app;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class SignUpActivity extends Activity {

    EditText firstNameNewUser;
    EditText lastNameNewUser;
    EditText emailNewUser;
    EditText passwordNewUser;
    EditText confirmNewUserPassword;
    EditText phoneNumberNewUser;
    EditText addressNewUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         firstNameNewUser = (EditText) findViewById(R.id.firstNameNewUser);
         lastNameNewUser = (EditText) findViewById(R.id.lastNameNewUser);
         emailNewUser = (EditText) findViewById(R.id.emailNewUser);
         passwordNewUser = (EditText) findViewById(R.id.passwordNewUser);
         confirmNewUserPassword = (EditText) findViewById(R.id.confirmNewUserPassword);
         phoneNumberNewUser = (EditText) findViewById(R.id.phoneNumberNewUser);
         addressNewUser = (EditText) findViewById(R.id.addressNewUser);




         final Button bRegister = (Button) findViewById(R.id.bRegister);
         bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = false;

                //1. call addUserToDB();
                addUsertoDB();
//                Intent goBackToLoginIntent = new Intent(SignUpActivity.this,LoginActivity.class);
//                SignUpActivity.this.startActivity(goBackToLoginIntent);
            }
        });
    }


    private void addUsertoDB() {
        //1. get inputs
        String name = firstNameNewUser.getText().toString().trim();
        String lastName = lastNameNewUser.getText().toString().trim();
        String email = emailNewUser.getText().toString().trim();
        String password = passwordNewUser.getText().toString().trim();
        String confirmPassword = confirmNewUserPassword.getText().toString().trim();
        String phoneNumber = phoneNumberNewUser.getText().toString().trim();
        String address = addressNewUser.getText().toString().trim();

        //2. validate input
        verifyInfo(name, lastName, email, password, confirmPassword, phoneNumber, address);

        //3. User registration


    }

    private void verifyInfo(String name, String lastName, String email, String password,
                                String confirmPassword, String phoneNumber, String address) {
        //if name is empty
        if (name.isEmpty()) {
            firstNameNewUser.setError("Name is required");
            firstNameNewUser.requestFocus();
            return;
        }

        //validate name
        if (!name.matches("[A-Z][a-zA-Z]*")) {
            firstNameNewUser.setError("Please enter a valid name");
            firstNameNewUser.requestFocus();
            return;
        }

        //if lastName is empty
        if (lastName.isEmpty()) {
            lastNameNewUser.setError("last name is required");
            lastNameNewUser.requestFocus();
            return;
        }

        //validate lastName
        if (!lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
            lastNameNewUser.setError("Please enter a valid last name");
            lastNameNewUser.requestFocus();
            return;
        }

        //if email is empty
        if (email.isEmpty()) {
            emailNewUser.setError("email is required");
            emailNewUser.requestFocus();
            return;
        }

        //validate email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailNewUser.setError("Please enter a valid email");
            emailNewUser.requestFocus();
            return;
        }

        //if password is empty
        if (password.isEmpty()) {
            passwordNewUser.setError("password is required");
            passwordNewUser.requestFocus();
            return;
        }

        //validate password
        if (password.length()<6) {
            passwordNewUser.setError("Minimum length of password should b 8 ");
            passwordNewUser.requestFocus();
            return;
        }

        //if confirmPassword is empty
        if (confirmPassword.isEmpty()) {
            confirmNewUserPassword.setError("You need to confirm your password");
            confirmNewUserPassword.requestFocus();
            return;
        }

        //validate confirmPassword
        if (!confirmPassword.equals(password)) {
            confirmNewUserPassword.setError("passwords do not match");
            confirmNewUserPassword.requestFocus();
            return;
        }

        //if phoneNumber is empty
        if (phoneNumber.isEmpty()) {
            phoneNumberNewUser.setError("phone number is required");
            phoneNumberNewUser.requestFocus();
            return;
        }

        //validate phoneNumber
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            phoneNumberNewUser.setError("Please enter a valid phone number");
            phoneNumberNewUser.requestFocus();
            return;
        }

        //if address is empty
        if (address.isEmpty()) {
            addressNewUser.setError("Name is required");
            addressNewUser.requestFocus();
            return;
        }

        //validate address
        if (!address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) {
            addressNewUser.setError("Please enter a valid address");
            addressNewUser.requestFocus();
            return;
        }

    }






}
