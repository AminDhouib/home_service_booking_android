package com.handy.agile.agile_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserAccountActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        final TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        final TextView tvRole = (TextView) findViewById(R.id.tvRole);
        final TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        final TextView tvLoggedInAs = (TextView) findViewById(R.id.tvLoggedInAs);
    }
}
