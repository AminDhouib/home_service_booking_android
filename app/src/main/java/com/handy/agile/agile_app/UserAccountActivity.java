package com.handy.agile.agile_app;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserAccountActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvRole;

    //list of users in the DB
    ListView listViewUsers;

    DatabaseReference databaseUser;

    List<User> users;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        setContentView(R.layout.activity_user_account);

        tvUsername = findViewById(R.id.tvUsername);
        tvUsername.setText("Welcome, " + user.getName());
        tvRole = findViewById(R.id.tvRole);
        tvRole.setText("You are logged in as " + user.getRole());

        listViewUsers = findViewById(R.id.listViewUsers);
        users = new ArrayList<>();

        databaseUser = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //if you're logged in as admin
        if (user.getRole().equals("Admin")) {
            listViewUsers.setVisibility(View.VISIBLE);
            displayDataBase();
        }



    }

    //Display database if Admin
    private void displayDataBase() {
        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                users.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    users.add(user);
                }
                UserList userAdapter = new UserList(UserAccountActivity.this,users);
                listViewUsers.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

