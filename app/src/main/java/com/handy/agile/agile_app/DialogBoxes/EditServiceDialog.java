package com.handy.agile.agile_app.DialogBoxes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.R;

public class EditServiceDialog extends DialogFragment implements View.OnClickListener {

    //This dialog box appears when the user clicks edit for one of the services. It prompts the user to edit new values
    //for the service name and hourly rate. It will update/delete the service in the database.

    private Activity activity;
    private Button save;
    private Button cancel;
    private Button delete;
    private DatabaseReference database;
    private TextView serviceTextView;
    private EditText hourlyRateTextView;
    private String service;
    private String hourlyRate;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View v = layoutInflater.inflate(R.layout.editservice_dialog, viewGroup, false);

        cancel = v.findViewById(R.id.cancelServiceChangesBtn);
        database = FirebaseDatabase.getInstance().getReference();
        save = v.findViewById(R.id.saveServiceChangesBtn);
        delete = v.findViewById(R.id.deleteServiceButton);

        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
        save.setOnClickListener(this);

        //Retrive arguments passed from activity, these are the service name and price
        Bundle args = getArguments();
        hourlyRateTextView = v.findViewById(R.id.servicePrice);
        serviceTextView = v.findViewById(R.id.serviceName);
        service = args.getString("Service");
        serviceTextView.setText(service);
        hourlyRate = args.getString("HourlyRate");
        hourlyRateTextView.setText(hourlyRate);


        return v;



    }
    //Handles the click events from the buttons of this dialog
    @Override
    public void onClick(View v){
        switch(v.getId()) {
            //Cancel clicked, close dialog
            case R.id.cancelServiceChangesBtn:
                dismiss();
                break;
            case R.id.saveServiceChangesBtn:
                //Check if user has changed the values, if yes then update DB


                try{
                    final Double newRate = Double.parseDouble(hourlyRateTextView.getText().toString());

                    if(!(hourlyRateTextView.getText().toString().equals(hourlyRate))){
                        database.child("services").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                    Service service = snapshot.getValue(Service.class);
                                    //Update value in the database
                                    if(service.getType().equals(serviceTextView.getText().toString())){
                                        DatabaseReference db = snapshot.getRef().child("hourlyRate");
                                        db.setValue(newRate);
                                        dismiss();
                                    }


                                }



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                }catch (NumberFormatException e){
                    hourlyRateTextView.setError("Invalid hourly rate");
                    hourlyRateTextView.requestFocus();
                }


                break;



            case R.id.deleteServiceButton:
                //Remove the service from the db
                database.child("services").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                            Service service = snapshot.getValue(Service.class);
                            if(service.getType().equals(serviceTextView.getText().toString())){
                                snapshot.getRef().removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                dismiss();
                break;
        }
    }
}
