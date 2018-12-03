package com.handy.agile.agile_app;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.HomeOwnerAccountActivities.RaitingsAdapter;
import com.handy.agile.agile_app.HomeOwnerAccountActivities.SearchByRatingsActivity;

import java.util.HashMap;

public class DBUtility {


    public void updateServicePRovider(final String ID) {
        final DataSnapshot[] toUpdate = new DataSnapshot[1];

        DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference("users"); //used to get refrence to  service provider

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals(ID)){
                        toUpdate[0] = snapshot;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseUsers = FirebaseDatabase.getInstance().getReference("serviceProviders"); //used to get refrence to  service provider

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    for (DataSnapshot snapshot1: snapshot.getChildren()){

                        if (snapshot1.getKey().equals(toUpdate[0].getKey())){
                            HashMap<String, Object> tempStore = new HashMap<>();
                            for (DataSnapshot copyChildren: toUpdate[0].getChildren()){
                                tempStore.put(copyChildren.getKey(),copyChildren.getValue());
                            }

                            snapshot1.getRef().updateChildren(tempStore);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
