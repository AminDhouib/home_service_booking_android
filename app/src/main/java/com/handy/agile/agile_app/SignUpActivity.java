package com.handy.agile.agile_app;

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
    }
}
