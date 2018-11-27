package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
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
import com.handy.agile.agile_app.ListClasses.ServiceList;
import com.handy.agile.agile_app.ListClasses.ServiceListForDeleting;
import com.handy.agile.agile_app.ListClasses.ServiceListForDisplay;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;
import com.handy.agile.agile_app.UtilityClasses.Rating;

import java.util.ArrayList;
import java.util.List;

public class SPPublicProfileActivity extends AppCompatActivity {

    private ServiceProvider serviceProvider;
    private User homeOwner;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceForRating;
    private List<Service> services;
    private TextView spFirstNameTextView;
    private TextView spLastNameTextView;
    private TextView companyNameTextView;
    private TextView licensedTextView;
    private ListView servicesListView;
    private TextView mondayTimeTextView;
    private TextView tuesdayTimeTextView;
    private TextView wednesdayTimeTextView;
    private TextView thursdayTimeTextView;
    private TextView fridayTimeTextView;
    private TextView saturdayTimeTextView;
    private TextView sundayTimeTextView;

    private EditText leaveACommentEditText;
    private Button saveRatingnButton;
    private TextView seeAllReviewsTextView;
    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        serviceProvider = (ServiceProvider)intent.getSerializableExtra("spInfo");
        homeOwner = (User)intent.getSerializableExtra("homeOwnerStuff");
        setContentView(R.layout.activity_sppublic_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReferenceForRating = FirebaseDatabase.getInstance().getReference("Ratings").child(serviceProvider.getId());
        services = new ArrayList<>();

        spFirstNameTextView = findViewById(R.id.spFirstNameTextView);
        spLastNameTextView = findViewById(R.id.spLastNameTextView);
        companyNameTextView = findViewById(R.id.companyNameTextView);
        licensedTextView = findViewById(R.id. licensedTextView);

        servicesListView = findViewById(R.id.servicesListView);

        mondayTimeTextView = findViewById(R.id.mondayTimeTextView);
        tuesdayTimeTextView = findViewById(R.id.tuesdayTimeTextView);
        wednesdayTimeTextView = findViewById(R.id.wednesdayTimeTextView);
        thursdayTimeTextView = findViewById(R.id.thursdayTimeTextView);
        fridayTimeTextView = findViewById(R.id.fridayTimeTextView);
        saturdayTimeTextView = findViewById(R.id.saturdayTimeTextView);
        sundayTimeTextView = findViewById(R.id.sundayTimeTextView);


        spFirstNameTextView.setText("Name: "+serviceProvider.getName());
        spLastNameTextView.setText("Last name: "+serviceProvider.getLastName());
        companyNameTextView.setText("Company: "+serviceProvider.getCompanyName());
        if (serviceProvider.getLicensed().equals("licensed")) {
            licensedTextView.setText("Licensed: Yes");
        } else {
            licensedTextView.setText("Licensed: No");
        }

        //Used to set up the availability
        TextView[] avail = new TextView[] {mondayTimeTextView, tuesdayTimeTextView, wednesdayTimeTextView, thursdayTimeTextView,
                fridayTimeTextView, saturdayTimeTextView, sundayTimeTextView};

        for (int i = 0; i < avail.length;i++) {
            DayEntry day = serviceProvider.getDaysOfWeek().get(i);
            if (day.isOpen()) {
                avail[i].setText(day.getStartTime()+" - "+day.getEndTime());
            } else {
                avail[i].setText("Closed");
            }
        }

        databaseReference.child("serviceProviders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshot1:snapshot.getChildren()) {
                        User userSearch = snapshot1.getValue(User.class);

                        if (userSearch.getEmail().equals(serviceProvider.getEmail())) {
                            getServices(snapshot.getKey());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ratingBar = findViewById(R.id.ratingBar);
        leaveACommentEditText = findViewById(R.id.leaveACommentEditText);
        saveRatingnButton = findViewById(R.id.saveRatingnButton);
        seeAllReviewsTextView = findViewById(R.id.seeAllReviewsTextView);

        //Set a listener for rating
        saveRatingnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add rating to the DB
                float score = ratingBar.getRating();
                String comment = leaveACommentEditText.getText().toString();

                //Create a rating
                Rating rating = new Rating(homeOwner.getEmail(),score,comment);
                databaseReferenceForRating.child(homeOwner.getId()).setValue(rating);

                //Clear edit text
                leaveACommentEditText.setText("");

                //Signal successful addition
                Toast toast  = Toast.makeText(getApplicationContext(),"Thank you for your feedback",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();

            }
        });

        seeAllReviewsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take to the ratings activity
                Intent viewAllRatingsItent = new Intent(SPPublicProfileActivity.this, ViewAllRatingsActivity.class);
                viewAllRatingsItent.putExtra("SPInQuestion", serviceProvider);
                SPPublicProfileActivity.this.startActivity(viewAllRatingsItent);
            }
        });


    }


    //Get info for a particulate service from DB and display it
    public void getServices(final String key) {
        DatabaseReference dbService = FirebaseDatabase.getInstance().getReference("services");
        dbService.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot3: dataSnapshot.getChildren()) {
                    Service service = snapshot3.getValue(Service.class);

                    //Add service to listview
                    if (service.getType().equals(key)) {
                        services.add(service);
                    }
                }
                ServiceListForDisplay adapter = new ServiceListForDisplay(SPPublicProfileActivity.this, services);
                servicesListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
