package com.handy.agile.agile_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddProvidedServiceDialog extends DialogFragment {

    Service service;
    User user;
    DatabaseReference databaseServiceProvider;
    DatabaseReference database;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        service = (Service)args.getSerializable("Service");
        database = FirebaseDatabase.getInstance().getReference();
        databaseServiceProvider =  database.child("serviceProviders");
        user = ((ServiceSearchActivity)getActivity()).getUser();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View v = layoutInflater.inflate(R.layout.add_provided_service_dialog, viewGroup, false);

        v.findViewById(R.id.confirmAddServiceToProvidedServicesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkService(service);
                Toast toast = Toast.makeText(getContext(), "Added you as a Service Provider!", Toast.LENGTH_LONG);
                toast.show();
                dismiss();
            }
        });

        v.findViewById(R.id.cancelAddToProvidedServicesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    //Method checks if the user is not already providing the service
    public void checkService(final Service service){
        databaseServiceProvider.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean found = false;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        User foundUser = snapshot1.getValue(User.class);
                        //if the key is the same as the sercvice type and the user name
                        // is the same as the user in the db, then the user is already providing this service.
                        if (snapshot.getKey().equals(service.getType()) && foundUser.getName().equals(user.getName())) {
                            //set flag to true
                            found = true;
                        }
                    }

                }
                //If the user was not found, add them as a service provider for this service
                if (!found) {
                    String key = databaseServiceProvider.child(service.getType()).push().getKey();
                    databaseServiceProvider.child(service.getType()).child(key).setValue(user);

                    //Otherwise the user was already a service provider so we will not add them
                } else {
                    //Display message saying they are already a service provider
//                    Toast toast = Toast.makeText(getContext(), "You already selected this service", Toast.LENGTH_LONG);
//                    toast.show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
