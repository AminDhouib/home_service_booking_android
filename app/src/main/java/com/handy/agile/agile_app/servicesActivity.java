package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class servicesActivity extends AppCompatActivity {

    EditText etServiceType;
    EditText etHourlyRate;

    Button addServiceButton;

    DatabaseReference database;
    DatabaseReference databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        //Get reference to DB
        database = FirebaseDatabase.getInstance().getReference();
        databaseService = database.child("services");

        //Initialize the textfields and button
        etServiceType = findViewById(R.id.etServiceType);
        etHourlyRate = findViewById(R.id.etHourlyRate);
        addServiceButton = findViewById(R.id.addServiceButton);
        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create service and add it to the DB

            }
        });

    }

    private void addServicetoDB() {
        //Get the contents of the text fields
        String serviceType = etServiceType.getText().toString().trim().toLowerCase();
        String hourlyRateValue = etHourlyRate.getText().toString();
        float hourlyRate = Float.parseFloat(hourlyRateValue);

        //Addd the service to the database Service
    }

    //Display table of the service DB
}
