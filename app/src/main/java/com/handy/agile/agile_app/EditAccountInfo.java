package com.handy.agile.agile_app;

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
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.Service;

public class EditAccountInfo extends DialogFragment implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
//R.layout.activity_edit_account_info
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View v = layoutInflater.inflate(R.layout.activity_edit_account_info, viewGroup, false);

        /*cancel = v.findViewById(R.id.cancelServiceChangesBtn);
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

*/

        //get data from passed bundle

        return v;



    }

    @Override
    public void onClick(View view) {

    }
}
