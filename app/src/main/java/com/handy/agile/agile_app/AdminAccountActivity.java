package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.handy.agile.agile_app.R;

import java.util.ArrayList;
import java.util.List;

public class AdminAccountActivity extends AppCompatActivity {

    TextView adminName;

    //list of users in the DB
    ListView listViewUsers;

    Button servicesButton;

    DatabaseReference databaseUser;

    List<User> users;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        setContentView(R.layout.activity_admin_account);

        adminName = findViewById(R.id.adminName);
        adminName.setText("Welcome, "+user.getName());

        listViewUsers = findViewById(R.id.listViewUsers);
        users = new ArrayList<>();

        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        servicesButton = findViewById(R.id.servicesButton);
        servicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take me to the services activity
                Intent serviceIntent = new Intent(AdminAccountActivity.this, servicesActivity.class);
                AdminAccountActivity.this.startActivity(serviceIntent);
            }
        });
        displayDataBase();

    }


    //Display database if admin
    private void displayDataBase() {
        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                users.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    users.add(user);
                }
                UserList userAdapter = new UserList(AdminAccountActivity.this,users);
                listViewUsers.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

