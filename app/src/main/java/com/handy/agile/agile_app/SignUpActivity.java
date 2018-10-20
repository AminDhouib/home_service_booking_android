package com.handy.agile.agile_app;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText firstNameNewUser = (EditText) findViewById(R.id.firstNameNewUser);
        final EditText lastNameNewUser = (EditText) findViewById(R.id.lastNameNewUser);
        final EditText emailNewUser = (EditText) findViewById(R.id.emailNewUser);
        final EditText passwordNewUser = (EditText) findViewById(R.id.passwordNewUser);
        final EditText confirmNewUserPassword = (EditText) findViewById(R.id.confirmNewUserPassword);
        final EditText phoneNumberNewUser = (EditText) findViewById(R.id.phoneNumberNewUser);
        final EditText addressNewUser = (EditText) findViewById(R.id.addressNewUser);

        final Button bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = false;
                //1. Call verifyInfo()

                //2. if valid:
                if (valid) {
                    // a. Call addAccountToDB()


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

    public boolean addAccountToDB() {
        //verify it was successfully added
        throw new UnsupportedOperationException("addAccountToDB() needs to be implemented");
    }



}
