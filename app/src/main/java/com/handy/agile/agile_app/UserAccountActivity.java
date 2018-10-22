package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserAccountActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("User");
        setContentView(R.layout.activity_user_account);

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUsername.setText("Welcome, " + user.getName());
        tvRole = (TextView) findViewById(R.id.tvRole);
        tvRole.setText("Role: " + user.getRole());

    }
}
