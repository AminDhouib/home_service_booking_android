package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.LoginActivity;
import com.handy.agile.agile_app.MainActivity;
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



        //Button to head to the search type activity.
        final Button searchTypebtn = findViewById(R.id.searchTypeButton);
        searchTypebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchTypeActivityIntent = new Intent(HomeOwnerAccountActivity.this,SearchByTypeActivity.class);
                HomeOwnerAccountActivity.this.startActivity(searchTypeActivityIntent);
            }
        });

        //Button to head to the search time activity.
        final Button searchTimebtn = findViewById(R.id.searchTimeButton);
        searchTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchTimeActivityIntent = new Intent(HomeOwnerAccountActivity.this,SearchByTimeActivity.class);
                HomeOwnerAccountActivity.this.startActivity(searchTimeActivityIntent);
            }
        });

        //Button to head to the search rating activity.
        final Button searchRatingbtn = findViewById(R.id.searchRatingButton);
        searchRatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchRatingActivityIntent = new Intent(HomeOwnerAccountActivity.this,SearchByRatingsActivity.class);
                HomeOwnerAccountActivity.this.startActivity(searchRatingActivityIntent);
            }
        });




    }
}
