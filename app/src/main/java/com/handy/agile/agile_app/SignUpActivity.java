package com.handy.agile.agile_app;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.os.Debug;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignUpActivity extends Activity {

    EditText firstNameNewUser;
    EditText lastNameNewUser;
    EditText emailNewUser;
    EditText passwordNewUser;
    EditText confirmNewUserPassword;
    EditText phoneNumberNewUser;
    EditText addressNewUser;
    Spinner spinnerRole;

    DatabaseReference databaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameNewUser = findViewById(R.id.firstNameNewUser);
        lastNameNewUser = findViewById(R.id.lastNameNewUser);
        emailNewUser = findViewById(R.id.emailNewUser);
        passwordNewUser = findViewById(R.id.passwordNewUser);
        confirmNewUserPassword = findViewById(R.id.confirmNewUserPassword);
        phoneNumberNewUser = findViewById(R.id.phoneNumberNewUser);
        addressNewUser = findViewById(R.id.addressNewUser);
        spinnerRole = findViewById(R.id.spinnerRole);

        //connecting to database
        databaseUser = FirebaseDatabase.getInstance().getReference();

        final Button bRegister = findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //First check if username is available
                checkUsername(emailNewUser.getText().toString().trim());
            }
        });

        //on touch it checks if there is an admin
        spinnerRole.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                checkAdmin();
                return false;
            }
        });
    }


    private void addUsertoDB() {
        //1. get inputs
        String name = firstNameNewUser.getText().toString().trim().toLowerCase();
        String lastName = lastNameNewUser.getText().toString().trim().toLowerCase();
        String email = emailNewUser.getText().toString().trim().toLowerCase();
        String password = passwordNewUser.getText().toString().trim();
        String confirmPassword = confirmNewUserPassword.getText().toString().trim();
        String phoneNumber = phoneNumberNewUser.getText().toString().trim();
        String address = addressNewUser.getText().toString().trim().toLowerCase();
        String role = spinnerRole.getSelectedItem().toString().trim().toLowerCase();

        //If the rest of the users information is valid, proceed to adding them to the database
        if (verifyInfo(name, lastName, email, password, confirmPassword, phoneNumber, address)) {


            String id = databaseUser.push().getKey();
            User newUser;
            if (role.equals("Home Owner")) {
                newUser = new HomeOwner(name, lastName, email, password, phoneNumber, address, role, id);

            } else if (role.equals("Service Provider")) {
                newUser = new ServiceProvider(name, lastName, email, password, phoneNumber, address, role, id);

            } else {
                newUser = new Admin(name, lastName, email, password, phoneNumber, address, role, id);

            }
            databaseUser.child(id).setValue(newUser);

            //4. Add user to database


            //Redirect to log in
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("registered", true);
            startActivity(intent);
        }


    }

    private boolean verifyInfo(String name, String lastName, String email, String password,
                               String confirmPassword, String phoneNumber, String address) {
        //if name is empty
        if (name.isEmpty()) {
            firstNameNewUser.setError("Name is required");
            firstNameNewUser.requestFocus();
            return false;
        }

        //validate name
        //if (!name.matches("[A-Z][a-zA-Z]*")) {
        if (!name.toString().matches("[a-zA-Z]+")) {
            firstNameNewUser.setError("Please enter a valid name");
            firstNameNewUser.requestFocus();
            return false;
        }

        //if lastName is empty
        if (lastName.isEmpty()) {
            lastNameNewUser.setError("last name is required");
            lastNameNewUser.requestFocus();
            return false;
        }

        //validate last name
        //if (!name.matches("[A-Z][a-zA-Z]*")) {
        if (!name.toString().matches("[a-zA-Z]+")) {
            lastNameNewUser.setError("Please enter a valid last name");
            lastNameNewUser.requestFocus();
            return false;
        }

        //if email is empty
        if (email.isEmpty()) {
            emailNewUser.setError("email is required");
            emailNewUser.requestFocus();
            return false;
        }

        //validate email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailNewUser.setError("Please enter a valid email");
            emailNewUser.requestFocus();
            return false;
        }

        //if password is empty
        if (password.isEmpty()) {
            passwordNewUser.setError("password is required");
            passwordNewUser.requestFocus();
            return false;
        }

        //validate password
        if (password.length() < 6) {
            passwordNewUser.setError("Minimum length of password should b 8 ");
            passwordNewUser.requestFocus();
            return false;
        }

        //if confirmPassword is empty
        if (confirmPassword.isEmpty()) {
            confirmNewUserPassword.setError("You need to confirm your password");
            confirmNewUserPassword.requestFocus();
            return false;
        }

        //validate confirmPassword
        if (!confirmPassword.equals(password)) {
            confirmNewUserPassword.setError("passwords do not match");
            confirmNewUserPassword.requestFocus();
            return false;
        }

        //if phoneNumber is empty
        if (phoneNumber.isEmpty()) {
            phoneNumberNewUser.setError("phone number is required");
            phoneNumberNewUser.requestFocus();
            return false;
        }

        //validate phoneNumber
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            phoneNumberNewUser.setError("Please enter a valid phone number");
            phoneNumberNewUser.requestFocus();
            return false;
        }

        //if address is empty
        if (address.isEmpty()) {
            addressNewUser.setError("Name is required");
            addressNewUser.requestFocus();
            return false;
        }

        //validate address
        if (!address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) {
            addressNewUser.setError("Please enter a valid address");
            addressNewUser.requestFocus();
            return false;
        }

        return true;
    }

    public void checkUsername(String username) {

        //Search in database for any user with the same email
        databaseUser.orderByChild("email").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Check ff the dataSnapshot contains non-null value, if it does not then the username is available and we will
                //proceed to checking the user input and add the user to the db
                if (!dataSnapshot.exists()) {
                    addUsertoDB();
                } else {
                    //Username unavailable, show warning
                    emailNewUser.setError("Username unavailable");
                    emailNewUser.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void checkAdmin() {
        //Search in database for any user with the same email
        databaseUser.orderByChild("role").equalTo("admin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //if the data exists, an Admin exists in the system
                    Log.d("SERVER", "THE RESULT OF THE QUEREY: " + dataSnapshot.exists());
                   /** if(dataSnapshot.exists()){
                        adminExists = true;

                    }else{
                        adminExists = false;
                    }*/
                if (dataSnapshot.exists()) {
                    //intialize XML string[] to a ArrayList
                    final List<String> roleList = new ArrayList<String>();
                    roleList.add("Home Owner");
                    roleList.add("Service Provider");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, roleList);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRole.setAdapter(adapter);
                }else{

                    //intialize XML string[] to a ArrayList
                    final List<String> roleList = new ArrayList<String>();
                    roleList.add("Home Owner");
                    roleList.add("Service Provider");
                    roleList.add("Admin");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, roleList);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRole.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
