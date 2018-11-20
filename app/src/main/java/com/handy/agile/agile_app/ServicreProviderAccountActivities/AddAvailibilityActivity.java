package com.handy.agile.agile_app.ServicreProviderAccountActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;
import com.handy.agile.agile_app.DialogBoxes.EditAvailabilityDialog;
import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.ListClasses.AvailabilityList;
import com.handy.agile.agile_app.R;

import java.util.ArrayList;
import java.util.List;

public class AddAvailibilityActivity extends AppCompatActivity {

    //Listview from the xml file
    ListView listViewAvailability;
    List<DayEntry> days;
    DatabaseReference databaseDays;




    //User object
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("UserInfo");
        setContentView(R.layout.activity_add_availibility);

        //Initialize textfiels
        listViewAvailability = findViewById(R.id.availabilityListView);
        days = new ArrayList<>();
        databaseDays = FirebaseDatabase.getInstance().getReference("users").child(user.getId()).child("daysOfWeek");
        displayDataBase();

        //Set listener on availability item
        listViewAvailability.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Display dialog box which changes the data
                DayEntry day = days.get(position);
                EditAvailabilityDialog dialog = new EditAvailabilityDialog();
                Bundle bundle = new Bundle();
                bundle.putSerializable("AvailabilityInfo",day);
                bundle.putSerializable("UserInfo",user);
                dialog.setArguments(bundle);

                FragmentActivity activity = AddAvailibilityActivity.this;
                dialog.show(activity.getSupportFragmentManager(),"EdtAvailability");

                return true;
            }
        });
    }

    //Display availibilities from DB
    private void displayDataBase() {
        databaseDays.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                days.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    DayEntry day = snapshot.getValue(DayEntry.class);

                    days.add(day);
                }
                AvailabilityList availabilityAdapter = new AvailabilityList(AddAvailibilityActivity.this,days);
                listViewAvailability.setAdapter(availabilityAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
