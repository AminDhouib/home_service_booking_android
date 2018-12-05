package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.ListClasses.SPListForTimeSearch;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchByRatingsActivity extends AppCompatActivity {

    RatingBar raitingsQuery;
    ListView searchByRaitingList;
    Button searchBtn;
    List<ServiceProvider> serviceProviders;
    List<ReveiwStore> storedReveiwes = new LinkedList<ReveiwStore>();

    DatabaseReference databaseReference;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_ratings);

        //Set UI references
        raitingsQuery = findViewById(R.id.rbSearch);
        searchByRaitingList = findViewById(R.id.lstRaitingsSearch);
        databaseReference = FirebaseDatabase.getInstance().getReference("Ratings"); //Used to access all the raitings
        serviceProviders = new ArrayList<>(); //lists all the service providers that will be displyed
        user = (User) getIntent().getSerializableExtra("homeOwnerInfo") ;

        //Raiting bar is begins set to 5 Stars, initial query.
        displayDataBase();
        searchByRaitingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceProvider sp = serviceProviders.get(position);
                Intent goToProfileIntent = new Intent(SearchByRatingsActivity.this, SPPublicProfileActivity.class);
                goToProfileIntent.putExtra("spInfo", sp);
                goToProfileIntent.putExtra("homeOwnerStuff", user);
                SearchByRatingsActivity.this.startActivity(goToProfileIntent);
            }});

        //Search button
        raitingsQuery.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                displayDataBase();
            }
        });

    }

    private void displayDataBase() {


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceProviders.clear();
                storedReveiwes.clear();
                //iterate through services
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //iterate through the service providers

                    //create a list to better organize all the reveiws
                    //List<ReveiwStore> lstRS = new LinkedList<ReveiwStore>();

                    for (DataSnapshot temp : snapshot.getChildren()) {
                        ReveiwStore rs = new ReveiwStore();
                        rs.setID(snapshot.getKey());
                        for (DataSnapshot tempChildren : temp.getChildren()) {
                            switch (tempChildren.getKey()) {
                                case ("comment"):
                                    rs.setComment(tempChildren.getValue().toString());
                                    break;
                                case ("reviewerEmail"):
                                    rs.setReviewerEmail(tempChildren.getValue().toString());
                                    break;
                                case ("score"):
                                    rs.setScore(Integer.parseInt(tempChildren.getValue().toString()));
                                    Log.d("Greg", "General rating "+rs.getScore());
                                    break;
                            }

                        }

                        if (rs.getScore() == (int) raitingsQuery.getRating()) {
                            addServiceProviderbyID(rs.getID());
                            storedReveiwes.add(rs);
                        }
                    }




                }
                Log.d("Greg", "Size sp: " + serviceProviders.size() + " Size reveiw:" + storedReveiwes.size());
                if (serviceProviders.size() == storedReveiwes.size()){
                    for (int i = 0; i < storedReveiwes.size(); i++){
                        storedReveiwes.get(i).setID(serviceProviders.get(i).getName());

                    }
                }
                RaitingsAdapter adapter = new RaitingsAdapter(SearchByRatingsActivity.this, storedReveiwes);
                searchByRaitingList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addServiceProviderbyID(final String ID) {
        DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference("users"); //used to get refrence to  service provider

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals(ID)){
                        Log.d("Greg", snapshot.toString()+"\n\n\n\n\n");
                        ServiceProvider serviceProvider = snapshot.getValue(ServiceProvider.class);
                        serviceProviders.add(serviceProvider);
                    }
                }

               // SPListForTimeSearch adapter = new SPListForTimeSearch(SearchByRatingsActivity.this, serviceProviders);
              //  searchByRaitingList.setAdapter(adapter);
                if (serviceProviders.size() == storedReveiwes.size()){
                    for (int i = 0; i < storedReveiwes.size(); i++){
                        storedReveiwes.get(i).setID(serviceProviders.get(i).getName());

                    }
                }
                RaitingsAdapter adapter = new RaitingsAdapter(SearchByRatingsActivity.this, storedReveiwes);
                searchByRaitingList.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

}
}
