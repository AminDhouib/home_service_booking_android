package com.handy.agile.agile_app.DialogBoxes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.Appointment;
import com.handy.agile.agile_app.DomainClasses.HomeOwner;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.DomainClasses.User;


import java.util.ArrayList;
import java.util.List;

public class BookAppointmentDialog extends DialogFragment {

    private TimePicker timepicker;
    private TextView availibility;
    private Spinner days;
    private User homeOwner;
    private ServiceProvider serviceProvider;
    private List<DayEntry> availibilities;
    private DatabaseReference database;
    private Button saveBtn;
    private DayEntry currentDay;



    public BookAppointmentDialog(){}

    @Override
    public void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View v = layoutInflater.inflate(R.layout.book_appointment_dialog, viewGroup, false);

        Bundle args = getArguments();


        homeOwner = (User)args.getSerializable("user");
        serviceProvider = (ServiceProvider)args.getSerializable("serviceProvider");


        database = FirebaseDatabase.getInstance().getReference();
        availibility = v.findViewById(R.id.availabilityTextView);
        timepicker = v.findViewById(R.id.bookAppointmentTimepicker);
        timepicker.setIs24HourView(true);
        days = v.findViewById(R.id.daysOfWeekSpinner);
        saveBtn = v.findViewById(R.id.saveAppointmentBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate the time so that is withing the SPs availibility
                if(validateTime(timepicker.getCurrentHour(), timepicker.getCurrentMinute())){
                    bookAppointment(timepicker.getCurrentHour(), timepicker.getCurrentMinute(), currentDay.getDay());
                    Toast toast = Toast.makeText(getContext(), "Booked Appointment!", Toast.LENGTH_LONG);
                    toast.show();
                    dismiss();
                }else{
                    Toast toast = Toast.makeText(getContext(), "unavailable", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        availibilities = serviceProvider.getDaysOfWeek();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.weekdays, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(adapter);

        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(DayEntry entry : availibilities){
                    if(entry.getDay().equals(parent.getItemAtPosition(position))) {
                        currentDay = entry;
                        if (!entry.isOpen()) {
                            timepicker.setEnabled(false);
                            saveBtn.setEnabled(false);
                            availibility.setText("Closed");
                        } else {
                            timepicker.setEnabled(true);
                            saveBtn.setEnabled(true);
                            availibility.setText("Availibilty " + entry.getStartTime() + " - " + entry.getEndTime());

                        }
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return v;
    }

    public boolean validateTime(int hour, int minute){
        if(hour < Integer.parseInt(currentDay.getStartTime().substring(0,2)) || hour > Integer.parseInt(currentDay.getEndTime().substring(0,2))){
            return false;
        }else if(hour == Integer.parseInt(currentDay.getStartTime().substring(0,2)) && minute < Integer.parseInt(currentDay.getStartTime().substring(3, 5))){
            return false;
        }else if(hour == Integer.parseInt(currentDay.getEndTime().substring(0,2)) && minute > Integer.parseInt(currentDay.getEndTime().substring(3,5))){
            return false;
        }

        return true;
    }

    //Method to add the appointment to the DB
    public void bookAppointment(int hour, int minute, String day){
        Appointment appointment = new Appointment();

        //Convert hour and minute to time string
        String hourString = "00".substring(Integer.toString(hour).length()) + Integer.toString(hour);
        String minString = "00".substring(Integer.toString(minute).length()) + Integer.toString(minute);
        String ampm = hour >= 12 ? "PM" : "AM";
        String time = hourString +":" + minString + ampm;

        appointment.setTime(time);
        appointment.setDay(day);
        appointment.setHomeOwner(homeOwner);
        appointment.setServiceProvider(serviceProvider);

        //Add appontment to the DB
        String key = database.push().getKey();
        database.child("Appointments").child(homeOwner.getId()).child(key).setValue(appointment);
    }


}
