package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button bSignUp;
    Button bLogin;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();

        //Check if we are coming from a new registration, if yes display toast
        if(intent.getBooleanExtra("registered", false)){
            Toast toast = Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        }


        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bSignUp = findViewById(R.id.bSingUp);
        bLogin = findViewById(R.id.bLogin);

        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                LoginActivity.this.startActivity(signUpIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyInfo(etEmail.getText().toString().toLowerCase(), etPassword.getText().toString());

            }
        });


    }

    //Verify username and password entered
    public boolean verifyInfo(String username, final String password) {

        //Search database for entries with emails equal to the one entered
        Query query = databaseUser.orderByChild("email").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //If the dataSnaphot is not null, then we have found a user with a username matching the one specified
                if(dataSnapshot.exists()) {

                    //Iterate through found results, there will be only one returned result in this case as all usernames are unique
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        //create a user with the returned data
                        User user = snapshot.getValue(User.class);

                        //check if this users password is the same as the one that was entered
                        if (user.getPassword().equals(password)){

                            //Correct password entered, start next activity. Pass the user object to the next activity
                            String role = user.getRole().toLowerCase();
                            Intent intent;

                            if (role.equals("home owner")) {
                                intent = new Intent(LoginActivity.this, HomeOwnerAccountActivity.class);
                                intent.putExtra("User",user);
                                LoginActivity.this.startActivity(intent);
                            }
                            else if (role.equals("service provider")) {
                                intent = new Intent(LoginActivity.this, ServiceProviderAccountActivity.class);
                                intent.putExtra("User",user);
                                user.getName();
                                if(user.getCompanyName().equals("Not yet specified")){
                                    intent = new Intent(LoginActivity.this, EditAccountActivity.class);
                                    intent.putExtra("ProfileInfo", new ServiceProvider(user));
                                    intent.putExtra("CompleteProfile", true);
                                    startActivity(intent);
                                }
//
                                LoginActivity.this.startActivity(intent);
                            } else {
                                intent = new Intent(LoginActivity.this, AdminAccountActivity.class);
                                intent.putExtra("User",user);
                                LoginActivity.this.startActivity(intent);
                            }


                        //Incorrect password was entered
                        }else{
                            etPassword.setError("Password Incorrect");
                            etPassword.requestFocus();
                        }
                    }
                    //No matching email was found in the database
                    }else{
                           etEmail.setError("Invalid username");
                           etEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return true;
    }




}
