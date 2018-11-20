package com.handy.agile.agile_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        txtCompany.setText(user.getCompanyName());
        txtDescription = findViewById(R.id.txtDescription);
        txtDescription.setText(user.getDescription());
        licenseSpinner = findViewById(R.id.licenseSpinner);

        if (user.getLicensed().equals("true")) {
            licenseSpinner.setSelection(1);
        } else {
            licenseSpinner.setSelection(0);
        }
        btnSave = findViewById(R.id.btnSave);


        databaseServiceProvider = FirebaseDatabase.getInstance().getReference("user").child(user.getId());
        //So we can capture this value

        //Set a listener on save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verify the info
                //Add to DB
            }
        });


    }

    //We can use this method to keep the user locked in this activity
    public boolean verifyInfo() {
        String name = txtFirstName.getText().toString();
        String lastName =txtLastName.getText().toString();
        String password = txtPassword.getText().toString();
        String phoneNumber = txtPhone.getText().toString();
        String address = txtAddress.getText().toString();
        String companyName = txtCompany.getText().toString();

        //if name is empty
        if (name.isEmpty()) {
            txtFirstName.setError("Name is required");
            txtFirstName.requestFocus();
            return false;
        }

        //validate name
        if (!name.toString().matches("[a-zA-Z]+")) {
            txtFirstName.setError("Please enter a valid name");
            txtFirstName.requestFocus();
            return false;
        }

        //if lastName is empty
        if (lastName.isEmpty()) {
            txtLastName.setError("Last name is required");
            txtLastName.requestFocus();
            return false;
        }

        //validate last name
        if (!lastName.toString().matches("[a-zA-Z]+")) {
            txtLastName.setError("Please enter a valid last name");
            txtLastName.requestFocus();
            return false;
        }


        //if password is empty
        if (password.isEmpty()) {
            txtPassword.setError("Password is required");
            txtPassword.requestFocus();
            return false;
        }

        //if phoneNumber is empty
        if (phoneNumber.isEmpty()) {
            txtPhone.setError("Phone number is required");
            txtPhone.requestFocus();
            return false;
        }

        //validate phoneNumber
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            txtPhone.setError("Please enter a valid phone number");
            txtPhone.requestFocus();
            return false;
        }

        //if address is empty
        if (address.isEmpty()) {
            txtAddress.setError("Address is required");
            txtAddress.requestFocus();
            return false;
        }

        //validate address
        if (!address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) {
            txtAddress.setError("Please enter a valid address");
            txtAddress.requestFocus();
            return false;
        }

        //validate company name
        if (companyName.isEmpty()) {
            txtCompany.setError("Company name is required");
            txtCompany.requestFocus();
            return false;
        }

        return true;
    }


    //Update the info
    public void updateInfo() {

    }
}
