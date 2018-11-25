package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.ListClasses.SPListForTimeSearch;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;

import java.util.ArrayList;
import java.util.List;

public class SearchByTimeActivity extends AppCompatActivity {

    EditText timeSearchEditText;
    ListView searchByTimeListView;
    Button searchBtn;
    List<ServiceProvider> serviceProviders;
    DatabaseReference databaseReference;
    String searchedQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_time);

        //initialize textfields
        timeSearchEditText = findViewById(R.id.timeSearchEditText);
        searchByTimeListView = findViewById(R.id.searchByTimeListView);
        searchBtn = findViewById(R.id.searchBtn);

        serviceProviders = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        searchByTimeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceProvider sp = serviceProviders.get(position);
                Intent goToProfileIntent = new Intent(SearchByTimeActivity.this,SPPublicProfileActivity.class);
                goToProfileIntent.putExtra("spInfo",sp);
                SearchByTimeActivity.this.startActivity(goToProfileIntent);
            }
        });

        //Search button
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchedQuery = timeSearchEditText.getText().toString();
                try {
                    //Make first letter upper case just in case
                    searchedQuery = searchedQuery.substring(0,1).toUpperCase()+searchedQuery.substring(1);
                    if (verifySearchedQuery()) {
                        displayDataBase();
                    } else {
                        timeSearchEditText.setError("Please enter a valid week day");
                        timeSearchEditText.requestFocus();
                    }

                } catch (Exception e) {
                    timeSearchEditText.setError("Please enter a valid week day");
                    timeSearchEditText.requestFocus();
                }


            }
        });
    }

    private void displayDataBase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceProviders.clear();

                //iterate through services
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    //iterate through the service providers
                    ServiceProvider serviceProvider = snapshot.getValue(ServiceProvider.class);

                    //Only do this for SPs
                    if (serviceProvider.getRole().equals("service provider")) {
                        List<DayEntry> daysOfWeek = serviceProvider.getDaysOfWeek();
                        //iterate through the day entries
                        for (int i = 0; i < daysOfWeek.size(); i++) {
                            DayEntry d = daysOfWeek.get(i);
                            if (d.getDay().equals(searchedQuery) && d.isOpen()) {
                                serviceProviders.add(serviceProvider);
                            }
                        }
                    }

                }
                SPListForTimeSearch adapter = new SPListForTimeSearch(SearchByTimeActivity.this,serviceProviders);
                searchByTimeListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean verifySearchedQuery() {
        return searchedQuery.equals("Monday") || searchedQuery.equals("Tuesday") || searchedQuery.equals("Wednesday") ||
                searchedQuery.equals("Thursday") || searchedQuery.equals("Friday") || searchedQuery.equals("Saturday") ||
                searchedQuery.equals("Sunday");
    }
}
