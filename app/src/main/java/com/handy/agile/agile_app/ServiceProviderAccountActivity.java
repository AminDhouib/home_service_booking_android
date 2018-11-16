package com.handy.agile.agile_app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
        this.context = this;
        serviceProviderName = findViewById(R.id.serviceProviderName);
        serviceProviderName.setText("Welcome, "+user.getName());

        searchServicesButton = findViewById(R.id.searchServicesButton);

        listViewYourServices = findViewById(R.id.listViewYourServices);
        yourServices = new ArrayList<>();

        searchServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchServiceActivityIntent = new Intent(ServiceProviderAccountActivity.this,ServiceSearchActivity.class);
                ServiceProviderAccountActivity.this.startActivity(searchServiceActivityIntent);
            }
        });


        Button editBtn = findViewById(R.id.btnEdit);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                   //Create new Dialog
                EditServiceDialog dialog = new EditServiceDialog();
                Bundle bundle = new Bundle();

                //Pass values to the dialog
                bundle.putString("Service", (String)textViewServiceType.getText());

                //removing text elements from numeric string so it can be easily edited.
                String tempHourlyRate = (String)textViewHourlyRate.getText();
                tempHourlyRate = tempHourlyRate.replace("$","").replace(",", "");
                bundle.putString("HourlyRate", tempHourlyRate);
                dialog.setArguments(bundle);

                //Show the Dialog
                FragmentActivity activity = (FragmentActivity) context;
                dialog.show(activity.getSupportFragmentManager(), "EditService");
                */

                //Create new Dialog
                EditAccountInfo dialog = new EditAccountInfo();
                Bundle bundle = new Bundle();

                bundle.putString("Email", user.getEmail());
                bundle.putString("FirstName", user.getName());
                bundle.putString("LastName", user.getLastName());
                bundle.putString("password", user.getPassword());
                bundle.putString("phone", user.getPhoneNumber());

                //Show the Dialog
                FragmentActivity activity = (FragmentActivity) context;
                dialog.show(activity.getSupportFragmentManager(), "EditService");

            }
        });


    }
}
