package com.handy.agile.agile_app;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceSearchActivity extends AppCompatActivity {

    TextView etServiceType;
    TextView etHourlyRate;


    List<Service> services;
    ListView listViewServices;

    DatabaseReference database;
    DatabaseReference databaseService;

    User user;
    Service service;

    TimePickerDialog timePickerDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //Get info of the profile that started this activity
        user = (User) intent.getSerializableExtra("SP");
        setContentView(R.layout.activity_service_search);

        //Get reference to DB
        database = FirebaseDatabase.getInstance().getReference();
        databaseService = database.child("services");


        listViewServices = findViewById(R.id.listViewServices);
        services = new ArrayList<>();

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Extract the service object from the clicked item
                service = (Service) services.get(position);

                //Add to the DB
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDataBase();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }



    //Display table of the service DB
    private void displayDataBase() {
        databaseService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                services.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);

                    services.add(service);
                }
                ServiceListForAdding userAdapter = new ServiceListForAdding(ServiceSearchActivity.this,services);
                listViewServices.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }










}
