package com.handy.agile.agile_app;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity {

     EditText firstNameNewUser;
     EditText lastNameNewUser;
     EditText emailNewUser;
     EditText passwordNewUser;
     EditText confirmNewUserPassword;
     EditText phoneNumberNewUser;
     EditText addressNewUser;
     Button bRegister;

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

         bRegister = (Button) findViewById(R.id.bRegister);
         bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = false;
                //1. Call verifyInfo()

                //2. if valid:
                if (valid) {
                    // a. Call addUserToDB()


                    // b. Go back to login page
                    Intent goBackToLoginIntent = new Intent(SignUpActivity.this,LoginActivity.class);
                    SignUpActivity.this.startActivity(goBackToLoginIntent);
                }


                //if not valid: change editTexts that were not valid
            }
        });
    }

    //verify the info put in all edit texts
    public boolean verifyInfo() {
        // 1. verify it's valid

        // 2. verify it doesn't already exist in the DB
        throw new UnsupportedOperationException("verfiyInfo() needs to be implemented");
    }

    public boolean addUserToDB() {
        //verify it was successfully added
        throw new UnsupportedOperationException("addAccountToDB() needs to be implemented");
    }



}
