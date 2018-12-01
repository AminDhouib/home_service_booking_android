package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.ListClasses.AvailabilityList;
import com.handy.agile.agile_app.ListClasses.ListForTypeSearch;
import com.handy.agile.agile_app.ListClasses.SPListForTimeSearch;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.ServicreProviderAccountActivities.AddAvailibilityActivity;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;

import java.util.ArrayList;
import java.util.List;

public class SearchByTypeActivity extends AppCompatActivity {

    EditText timeSearchEditText;
    ListView listViewServices;
    List<ServiceProvider> serviceProviders;
    DatabaseReference database;
    String str;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_type);

        Intent intent = getIntent();

        user = (User)intent.getSerializableExtra("homeOwnerInfo");

        //Initialize textfiels
        final TextView textview = findViewById(R.id.whatToSearchType);
        timeSearchEditText = findViewById(R.id.whatToSearchType);
        listViewServices = findViewById(R.id.listViewServices);
        serviceProviders = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("serviceProviders");

        final Button searchTypebtnApply = findViewById(R.id.applySearchTypeButton);
        searchTypebtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String text = getTextViewContent();

                str = textview.getText().toString();

                try {
                    //Make first letter lower case
                    str = str.substring(0,1).toLowerCase()+str.substring(1);
                    if (!isSearchBoxInvalid()) {
                        displayDataBase();
                    } else {
                        timeSearchEditText.setError("Please enter a type!");
                        timeSearchEditText.requestFocus();
                    }

                } catch (Exception e) {
                    timeSearchEditText.setError("Please enter a type!");
                    timeSearchEditText.requestFocus();
                }

                str = textview.getText().toString();

            }
        });

       listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceProvider sp = serviceProviders.get(position);
                Intent goToProfileIntent = new Intent(SearchByTypeActivity.this,SPPublicProfileActivity.class);
                goToProfileIntent.putExtra("spInfo",sp);
                goToProfileIntent.putExtra("homeOwnerStuff",user);
                SearchByTypeActivity.this.startActivity(goToProfileIntent);
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
                SPListForTimeSearch adapter = new SPListForTimeSearch(SearchByTypeActivity.this, serviceProviders);
                listViewServices.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //checks if empty or there is a number inside.
    public boolean isSearchBoxInvalid() {
        return (str.equals("")) || str.matches(".*\\d+.*") || (!(str.matches("[a-zA-Z]+")));
    }


}
