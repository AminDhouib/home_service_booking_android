package com.handy.agile.agile_app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderAccountActivity extends AppCompatActivity {

    TextView serviceProviderName;

    Button searchServicesButton;
    Button editBtn;
    Button availBtn;
    ListView listViewYourServices;

    List<Service> yourServices;

    User user;

    DatabaseReference databaseReference;

    private Activity context;


    //Needed for resetting the activity to refresh the database's info
    @Override
    public void onRestart(){
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        setContentView(R.layout.activity_service_provider_account);
        context = this;
        serviceProviderName = findViewById(R.id.serviceProviderName);
        serviceProviderName.setText("Welcome, "+user.getName());

        searchServicesButton = findViewById(R.id.searchServicesButton);

        listViewYourServices = findViewById(R.id.listViewYourServices);
        yourServices = new ArrayList<>();

        searchServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchServiceActivityIntent = new Intent(ServiceProviderAccountActivity.this,ServiceSearchActivity.class);
                searchServiceActivityIntent.putExtra("SP",user);
                ServiceProviderAccountActivity.this.startActivity(searchServiceActivityIntent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        //Get services that this user is providing
        databaseReference.child("serviceProviders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //iterate through services
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //iterate through providers of service
                    for(final DataSnapshot snapshot1 : snapshot.getChildren()){
                        User userSearch = snapshot1.getValue(User.class);
                        //If the user email is the same as the email of service provider in Db, then the user is
                        //providing this service to we will get the info of this service to display
                        if(userSearch.getEmail().equals(user.getEmail())){
                                getServices(snapshot.getKey());



                        }
                    }
                }

                  ServiceListForAdding adapter = new ServiceListForAdding(ServiceProviderAccountActivity.this, yourServices);
                  listViewYourServices.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ;



        editBtn = findViewById(R.id.btnEdit);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create new Dialog
                EditAccountInfo dialog = new EditAccountInfo();
                Bundle bundle = new Bundle();

                dialog.setArguments(bundle);

                bundle.putString("Email", user.getEmail());
                bundle.putString("FirstName", user.getName());
                bundle.putString("LastName", user.getLastName());
                bundle.putString("Password", user.getPassword());
                bundle.putString("Phone", user.getPhoneNumber());
                bundle.putString("Address", user.getAddress());

                //Show the Dialog
                //FragmentActivity activity = (FragmentActivity) ;
                dialog.show(getSupportFragmentManager(), "EditUser");

                //now refresh the activity

            }
        });

        availBtn = findViewById(R.id.availBtn);
        availBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avail = new Intent(ServiceProviderAccountActivity.this,AddAvailibilityActivity.class);
                avail.putExtra("UserInfo",user);
                ServiceProviderAccountActivity.this.startActivity(avail);
            }
        });




    }

    //Gets the info for a particular service from DB and displays it
    public void getServices(final String key){
        databaseReference.child("services").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot3 : dataSnapshot.getChildren()){
                    Service service = snapshot3.getValue(Service.class);
                    //Add service to list view
                    if(service.getType().equals(key)){
                        yourServices.add(service);
                    }

                }
                ServiceListForAdding userAdapter = new ServiceListForAdding(ServiceProviderAccountActivity.this,yourServices);
                listViewYourServices.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
    });
}}
