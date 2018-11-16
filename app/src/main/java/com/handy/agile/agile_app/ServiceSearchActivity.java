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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
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

                //open the dialog box
                showUpdatedAddDialog(service,user);
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

    private void showUpdatedAddDialog(Service service, User user) {
        AlertDialog.Builder  dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addtoyourservices_dialog,null);
        dialogBuilder.setView(dialogView);


        dialogBuilder.setTitle(service.getType());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        final  TextView monday = dialogView.findViewById(R.id.monday);
        //I want to make it so that clicking  on this will prop a time picker
        final TextView mondayStartTime = dialogView.findViewById(R.id.mondayStartTime);

        //I want to make it so that clicking on this will prop a time picker
        final TextView mondayEndTime = dialogView.findViewById(R.id.mondayEndTime);

        final Spinner stateSpinnerMonday = dialogView.findViewById(R.id.stateSpinnerMonday);
        final TextView mondayDash = dialogView.findViewById(R.id.mondayDashTextView);

        //Disable if necessary for Monday
        disableTextViewsIfNecessary(stateSpinnerMonday,mondayStartTime,mondayDash,mondayEndTime);

        //Choose start time and end time for Monday
        chooseTime(mondayStartTime);
        chooseTime(mondayEndTime);




        //Add the 'add' button to the layout and set to listener
    }

    //This class disables the edit texts for the time if user selected closed
    public void disableTextViewsIfNecessary(Spinner spinner, final TextView startTime, final TextView dash, final TextView endTime) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getSelectedItem().toString();
                if (selectedItem.equals("Open")) {
                    startTime.setVisibility(View.VISIBLE);
                    dash.setVisibility(View.VISIBLE);
                    endTime.setVisibility(View.VISIBLE);
                } else {
                    startTime.setVisibility(View.INVISIBLE);
                    dash.setVisibility(View.INVISIBLE);
                    endTime.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //This will create a time picker object to choose start time for a specific day
    public void chooseTime(final TextView startTime) {
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            //We want it that clicking this will show the time picker
            public void onClick(View v) {

                //Initialize the time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ServiceSearchActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPM = "";
                        if (hourOfDay >= 12) {
                            amPM = "PM";
                        } else {
                            amPM = "AM";
                        }

                        String minuteValue = Integer.toString(minutes);
                        if (minutes < 10) {
                            minuteValue = "0"+minutes;
                        }
                        startTime.setText(hourOfDay+":"+minuteValue+amPM);
                    }
                }, 0,0, false);
                timePickerDialog.show();
            }
        });
    }



}
