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

        //Monday start and end time
        TextView mondayStartTime = dialogView.findViewById(R.id.mondayStartTime);
        TextView mondayEndTime = dialogView.findViewById(R.id.mondayEndTime);
        TextView mondayDash = dialogView.findViewById(R.id.mondayDashTextView);
        Spinner stateSpinnerMonday = dialogView.findViewById(R.id.stateSpinnerMonday);

        //Monday start and end time
        TextView tuesdayStartTime = dialogView.findViewById(R.id.tuesdayStartTime);
        TextView tuesdayEndTime = dialogView.findViewById(R.id.tuesdayEndTime);
        TextView tuesdayDash = dialogView.findViewById(R.id.tuesdayDashTextView);
        Spinner stateSpinnerTuesday = dialogView.findViewById(R.id.stateSpinnerTuesday);

        //Monday start and end time
        TextView wednesdayStartTime = dialogView.findViewById(R.id.wednesdayStartTime);
        TextView wednesdayEndTime = dialogView.findViewById(R.id.wednesdayEndTime);
        TextView wednesdayDash = dialogView.findViewById(R.id.wednesdayDashTextView);
        Spinner stateSpinnerWednesday = dialogView.findViewById(R.id.stateSpinnerWednesday);

        //Monday start and end time
        TextView thursdayStartTime = dialogView.findViewById(R.id.thursdayStartTime);
        TextView thursdayEndTime = dialogView.findViewById(R.id.thursdayEndTime);
        TextView thursdayDash = dialogView.findViewById(R.id.thursdayDashTextView);
        Spinner stateSpinnerThursday = dialogView.findViewById(R.id.stateSpinnerThursday);

        //Monday start and end time
        TextView fridayStartTime = dialogView.findViewById(R.id.fridayStartTime);
        TextView fridayEndTime = dialogView.findViewById(R.id.fridayEndTime);
        TextView fridayDash = dialogView.findViewById(R.id.fridayDashTextView);
        Spinner stateSpinnerFriday = dialogView.findViewById(R.id.stateSpinnerFriday);

        //Monday start and end time
        TextView saturdayStartTime = dialogView.findViewById(R.id.saturdayStartTime);
        TextView saturdayEndTime = dialogView.findViewById(R.id.saturdayEndTime);
        TextView saturdayDash = dialogView.findViewById(R.id.saturdayDashTextView);
        Spinner stateSpinnerSaturday = dialogView.findViewById(R.id.stateSpinnerSaturday);

        //Monday start and end time
        TextView sundayStartTime = dialogView.findViewById(R.id.sundayStartTime);
        TextView sundayEndTime = dialogView.findViewById(R.id.sundayEndTime);
        TextView sundayDash = dialogView.findViewById(R.id.sundayDashTextView);
        Spinner stateSpinnerSunday = dialogView.findViewById(R.id.stateSpinnerSunday);

        Button addtoYourServicesBtn = dialogView.findViewById(R.id.addtoYourServicesBtn);


        //Disable if times if closed for Monday
        disableTextViewsIfNecessary(stateSpinnerMonday,mondayStartTime,mondayDash,mondayEndTime);
        //Choose start time and end time for Monday using time picker
        chooseTime(mondayStartTime); chooseTime(mondayEndTime);



        //Disable if times if closed for Tuesday
        disableTextViewsIfNecessary(stateSpinnerTuesday,tuesdayStartTime,tuesdayDash,tuesdayEndTime);
        //Choose start time and end time for Monday using time picker
        chooseTime(tuesdayStartTime); chooseTime(tuesdayEndTime);

        //Disable if times if closed for Wednesday
        disableTextViewsIfNecessary(stateSpinnerWednesday,wednesdayStartTime,wednesdayDash,wednesdayEndTime);
        //Choose start time and end time for Monday using time picker
        chooseTime(wednesdayStartTime); chooseTime(wednesdayEndTime);

        //Disable if times if closed for Thursday
        disableTextViewsIfNecessary(stateSpinnerThursday,thursdayStartTime,thursdayDash,thursdayEndTime);
        //Choose start time and end time for Monday using time picker
        chooseTime(thursdayStartTime); chooseTime(thursdayEndTime);

        //Disable if times if closed for Friday
        disableTextViewsIfNecessary(stateSpinnerFriday,fridayStartTime,fridayDash,fridayEndTime);
        //Choose start time and end time for Monday using time picker
        chooseTime(fridayStartTime); chooseTime(fridayEndTime);

        //Disable if times if closed for Saturday
        disableTextViewsIfNecessary(stateSpinnerSaturday,saturdayStartTime,saturdayDash,saturdayEndTime);
        //Choose start time and end time for Monday using time picker
        chooseTime(saturdayStartTime); chooseTime(saturdayEndTime);

        //Disable if times if closed for Sunday
        disableTextViewsIfNecessary(stateSpinnerSunday,sundayStartTime,sundayDash,sundayEndTime);
        //Choose start time and end time for Monday using time picker
        chooseTime(sundayStartTime); chooseTime(sundayEndTime);


        addtoYourServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verify the times are correct

                //Add to DB
            }
        });






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
    public void chooseTime(final TextView time) {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            //We want it that clicking this will show the time picker
            public void onClick(View v) {

                //Initialize the time picker dialog
                timePickerDialog = new TimePickerDialog(ServiceSearchActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        String hourValue = Integer.toString(hourOfDay);
                        String minuteValue = Integer.toString(minutes);
                        String amPM;
                        if (hourOfDay >=12) {
                            amPM = "PM";
                        } else {
                            amPM = "AM";
                            if (hourOfDay < 10) {
                                hourValue = "0"+hourOfDay;
                            }
                        }
                        if (minutes < 10) {
                            minuteValue = "0"+minutes;
                        }
                        time.setText(hourValue+":"+minuteValue+amPM);

                    }
                }, 0,0, false);
                timePickerDialog.show();

            }
        });
    }

    public void chooseEndTime(String startHour, final TextView endTime) {

//        int hour = Integer.parseInt(startT);


        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            //We want it that clicking this will show the time picker
            public void onClick(View v) {

                //Initialize the time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ServiceSearchActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        String hourValue = Integer.toString(hourOfDay);
                        String minuteValue = Integer.toString(minutes);
                        String amPM;
                        if (hourOfDay >=12) {
                            amPM = "PM";
                        } else {
                            hourValue = "0"+hourOfDay;
                            amPM = "AM";
                        }
                        if (minutes < 10) {
                            minuteValue = "0"+minutes;
                        }
                        endTime.setText(hourValue+":"+minuteValue+amPM);


                    }
                }, 0,0, false);
                timePickerDialog.show();
            }
        });
    }










}
