package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.ListClasses.RatingList;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.UtilityClasses.Rating;

import java.util.ArrayList;
import java.util.List;

public class ViewAllRatingsActivity extends AppCompatActivity {

    private ServiceProvider serviceProvider;
    private DatabaseReference databaseReferenceRatings;
    private TextView titleTextView;
    private ListView ratingsListView;
    private List<Rating> ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        serviceProvider = (ServiceProvider)intent.getSerializableExtra("SPInQuestion");
        setContentView(R.layout.activity_view_all_ratings);

        databaseReferenceRatings = FirebaseDatabase.getInstance().getReference("Ratings").child(serviceProvider.getId());
        ratings = new ArrayList<>();

        titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText("Ratings for "+serviceProvider.getName());
        ratingsListView = findViewById(R.id.ratingsListView);

        //Display reviews
        displayDatabase();


    }

    private void displayDatabase() {
        databaseReferenceRatings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ratings.clear();

                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Rating rating = snapshot.getValue(Rating.class);

                    ratings.add(rating);

                }
                RatingList ratingAdapter = new RatingList(ViewAllRatingsActivity.this, ratings);
                ratingsListView.setAdapter(ratingAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
