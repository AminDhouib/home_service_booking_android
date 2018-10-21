package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bSignUp = (Button) findViewById(R.id.bSingUp);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(LoginActivity.this,SignUpActivity.class);
                LoginActivity.this.startActivity(signUpIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = false;
                //1. call verifyInfo()

                if (valid) {
                    // Intent to go to userAccountActivity
                }

                //if not valid: change editText for username and password to display
                //"invalid username or password


            }
        });


    }

    //Verify username and password entered
    public boolean verifyInfo() {
        throw new UnsupportedOperationException("verifyInfo() not implemented yet");
    }




}
