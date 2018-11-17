package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AddAvailibilityActivity extends AppCompatActivity {

    //Textviews from the xml file
    TextView serviceName;
    TextView mondayStartTime;
    TextView mondayEndTime;

    //Add button


    //User object
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("UserInfo");
        setContentView(R.layout.activity_add_availibility);

        //Initialize textfiels

        //Call the choose time method

        //set add button as click listener
    }

    //Choose time method

    //Verify time method

    //Add to the DB method
}
