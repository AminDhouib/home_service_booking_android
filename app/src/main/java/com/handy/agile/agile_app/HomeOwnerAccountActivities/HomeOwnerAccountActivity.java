package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.R;

public class HomeOwnerAccountActivity extends AppCompatActivity {

    TextView homeOwnerName;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        setContentView(R.layout.activity_home_owner_account);

        homeOwnerName = findViewById(R.id.homeOwnerName);
        homeOwnerName.setText("Welcome, "+user.getName());
    }
}
