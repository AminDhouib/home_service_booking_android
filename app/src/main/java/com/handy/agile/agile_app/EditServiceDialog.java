package com.handy.agile.agile_app;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EditServiceDialog extends DialogFragment implements View.OnClickListener {

    //This dialog box appears when the user clicks edit for one of the services. It prompts the user to edit new values
    //for the service name and hourly rate

    private Activity activity;
    private Button save;
    private Button cancel;
    private Button delete;
    private DatabaseReference database;
    private EditText serviceTextView;
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
        cancel.setOnClickListener(this);
        delete = v.findViewById(R.id.deleteServiceButton);
        delete.setOnClickListener(this);


        save.setOnClickListener(this);
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

                if(!(hourlyRateTextView.getText().toString().equals(hourlyRate))){
                    database.child("services").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Service service = snapshot.getValue(Service.class);
                                //Update value in the database
                                if(service.getType().equals(serviceTextView.getText().toString())){
                                    DatabaseReference db = snapshot.getRef().child("hourlyRate");
                                    db.setValue(Double.parseDouble(hourlyRateTextView.getText().toString()));
                                }
                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                dismiss();
                break;



            case R.id.deleteServiceButton:

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
