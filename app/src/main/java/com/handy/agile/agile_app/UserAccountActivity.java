package com.handy.agile.agile_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserAccountActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvRole;
    TextView tvWelcome;
    TextView tvLoggedInAs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvRole = (TextView) findViewById(R.id.tvRole);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvLoggedInAs = (TextView) findViewById(R.id.tvLoggedInAs);
    }
}
