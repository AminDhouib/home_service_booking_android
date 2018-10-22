package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bSignUp = (Button) findViewById(R.id.bSingUp);
        bLogin = (Button) findViewById(R.id.bLogin);
        databaseUser = FirebaseDatabase.getInstance().getReference();

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
                boolean valid = false;
                verifyInfo(etEmail.getText().toString(), etPassword.getText().toString());

                if (valid) {
                    // Intent to go to userAccountActivity
                }

                //if not valid: change editText for username and password to display
                //"invalid username or password


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
                            Intent intent = new Intent(LoginActivity.this, UserAccountActivity.class);

                            //Correct password entered, start next activity. Pass the user object to the next activity
                            intent.putExtra("User",user);
                            LoginActivity.this.startActivity(intent);

                        //Incorrect password was entered
                        }else{
                            Toast.makeText(getApplicationContext(),"Password does not match", Toast.LENGTH_LONG).show();
                        }
                    }
                    //No matching email was found in the database
                    }else{
                    Toast.makeText(getApplicationContext(),"Invalid login", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return true;
    }




}
