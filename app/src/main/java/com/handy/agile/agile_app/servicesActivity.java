package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                checkService(etServiceType.getText().toString().trim().toLowerCase());

            }
        });

    }

    private void addServicetoDB() {
        //Get the contents of the text fields
        String serviceType = etServiceType.getText().toString().trim().toLowerCase();
        String hourlyRateValue = etHourlyRate.getText().toString();

        if (verifyInfo(serviceType,hourlyRateValue)) {
            float hourlyRate = Float.parseFloat(hourlyRateValue);

            //Add the service to the database Service
            String id = databaseService.push().getKey();
            Service newService = new Service(serviceType,hourlyRate);
            databaseService.child(id).setValue(newService);

            //Clear content of the textfields
            etServiceType.setText("");
            etHourlyRate.setText("");


            //Signal successful addition
            Toast toast = Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        }


    }

    //Verify info
    private boolean verifyInfo(String serviceType, String hourlyRateValue) {
        //If the textfields are empty
        if (serviceType.isEmpty()) {
            etServiceType.setError("Enter a service");
            etServiceType.requestFocus();
            return false;
        }

        if (hourlyRateValue.isEmpty()) {
            etHourlyRate.setError("Enter an hourly rate");
            etHourlyRate.requestFocus();
            return false;
        }
        try {
            float hourlyRate = Float.parseFloat(hourlyRateValue);

        } catch(NullPointerException e1) {
            etHourlyRate.setError("Please enter a valid rate");
            etHourlyRate.requestFocus();
            return false;
        } catch(NumberFormatException e2) {
            etHourlyRate.setError("Please enter a valid rate");
            etHourlyRate.requestFocus();
            return false;
        }
        return true;
    }

   //Check service doesn't already exist
    private void checkService(String serviceType) {
        databaseService.orderByChild("type").equalTo(serviceType).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Check if the dataSnapshot contains non-null value, if it does not then the service is available and we will
                //proceed to checking the user input and add the user to the db
                if(!dataSnapshot.exists()) {
                    addServicetoDB();
                } else {
                    etServiceType.setError("This service is already available");
                    etServiceType.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Display table of the service DB
}
