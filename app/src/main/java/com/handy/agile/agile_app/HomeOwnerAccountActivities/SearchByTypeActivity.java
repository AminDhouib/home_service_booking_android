package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.ListClasses.AvailabilityList;
import com.handy.agile.agile_app.ListClasses.ListForTypeSearch;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.ServicreProviderAccountActivities.AddAvailibilityActivity;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;

import java.util.ArrayList;
import java.util.List;

public class SearchByTypeActivity extends AppCompatActivity {

    ListView listViewServices;
    List<ServiceProvider> serviceProviders;
    DatabaseReference database;
    String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_type);

        //Initialize textfiels
        final TextView textview = findViewById(R.id.whatToSearchType);
        listViewServices = findViewById(R.id.listViewServices);
        serviceProviders = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("serviceProviders");

        final Button searchTypebtnApply = findViewById(R.id.applySearchTypeButton);
        searchTypebtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String text = getTextViewContent();

                str = textview.getText().toString();


                displayDataBase();



            }
        });
    }


    //Display availibilities from DB
    private void displayDataBase() {
        database.child(str).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                serviceProviders.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    ServiceProvider serviceProvider = snapshot.getValue(ServiceProvider.class);

                    serviceProviders.add(serviceProvider);
                }
                ListForTypeSearch searchTypeAdapter = new ListForTypeSearch(SearchByTypeActivity.this,serviceProviders);
                listViewServices.setAdapter(searchTypeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
