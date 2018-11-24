package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.ListClasses.AvailabilityList;
import com.handy.agile.agile_app.ListClasses.ListForTypeSearch;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.ServicreProviderAccountActivities.AddAvailibilityActivity;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;

import java.util.ArrayList;
import java.util.List;

public class SearchByTypeActivity extends AppCompatActivity {

    ListView listViewServices;
    List<Service> services;
    DatabaseReference databaseDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_type);

        //Initialize textfiels
        listViewServices = findViewById(R.id.listViewServices);
        services = new ArrayList<>();
        databaseDays = FirebaseDatabase.getInstance().getReference("services");

        final Button searchTypebtnApply = findViewById(R.id.applySearchTypeButton);
        searchTypebtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDataBase();
            }
        });
    }

    //Display availibilities from DB
    private void displayDataBase() {
        databaseDays.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                services.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);

                    services.add(service);
                }
                ListForTypeSearch searchTypeAdapter = new ListForTypeSearch(SearchByTypeActivity.this,services);
                listViewServices.setAdapter(searchTypeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
