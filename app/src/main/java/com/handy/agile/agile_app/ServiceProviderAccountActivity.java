package com.handy.agile.agile_app;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        setContentView(R.layout.activity_service_provider_account);

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



    }
}
