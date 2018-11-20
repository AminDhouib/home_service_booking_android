package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditAccountActivity extends AppCompatActivity {

    private TextView txtEmail;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private EditText txtPhone;
    private EditText txtPassword;
    private EditText txtCompany;
    private EditText txtDescription;
    private Spinner licenseSpinner;
    private Button btnSave;

    private User user;
    private DatabaseReference databaseServiceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("ProfileInfo");
        setContentView(R.layout.activity_edit_account);

        txtEmail = findViewById(R.id.txtEmail);
        txtEmail.setText(user.getEmail());
        txtFirstName = findViewById(R.id.txtFirstName);
        txtFirstName.setText(user.getName());
        txtLastName = findViewById(R.id.txtLastName);
        txtLastName.setText(user.getLastName());
        txtAddress = findViewById(R.id.txtAdress);
        txtAddress.setText(user.getAddress());
        txtPhone = findViewById(R.id.txtPhone);
        txtPhone.setText(user.getPhoneNumber());
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword.setText(user.getPassword());
        txtCompany = findViewById(R.id.txtCompanyName);
//        txtCompany.setText();
        txtDescription = findViewById(R.id.txtDescription);
        licenseSpinner = findViewById(R.id.licenseSpinner);
        btnSave = findViewById(R.id.btnSave);

//        databaseServiceProvider = FirebaseDatabase.getInstance().getReference(user.getId()).child()


        //Set a listener on save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verify the info
                //Add to DB
            }
        });


    }


    public boolean verifyInfo() {
        return false;
    }
}
