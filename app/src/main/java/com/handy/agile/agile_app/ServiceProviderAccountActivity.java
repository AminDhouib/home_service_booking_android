package com.handy.agile.agile_app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

    private Activity context;

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
}
