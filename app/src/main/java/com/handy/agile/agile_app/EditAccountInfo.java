package com.handy.agile.agile_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.Service;

import java.util.HashMap;
import java.util.Map;

public class EditAccountInfo extends DialogFragment implements View.OnClickListener {


    private DatabaseReference database;

    //Buttons
    private Button cancel;
    private Button save;

    //Text Feilds
    TextView emailView;
    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText passwordEdit;
    EditText addressEdit;
    EditText phoneEdit;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
//R.layout.activity_edit_account_info
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View v = layoutInflater.inflate(R.layout.activity_edit_account_info, viewGroup, false);
        database = FirebaseDatabase.getInstance().getReference().child("users");
        cancel = v.findViewById(R.id.btnCancel);
        save = v.findViewById(R.id.btnSave);


        Bundle args = getArguments();

        emailView = v.findViewById(R.id.txtEmail);
        emailView.setText(args.getString("Email"));
        firstNameEdit = v.findViewById(R.id.txtFirstName);
        firstNameEdit.setText(args.getString("FirstName"));
        lastNameEdit = v.findViewById(R.id.txtLastName);
        lastNameEdit.setText(args.getString("LastName"));
        passwordEdit = v.findViewById(R.id.txtPassword);
        passwordEdit.setText(args.getString("Password"));
        addressEdit = v.findViewById(R.id.txtAdress);
        addressEdit.setText(args.getString("Address"));
        phoneEdit = v.findViewById(R.id.txtPhone);
        phoneEdit.setText(args.getString("Phone"));


        //get data from passed bundle
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
        return v;



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnSave:
                //
                if (verifyInfo(firstNameEdit.getText().toString(),lastNameEdit.getText().toString(),passwordEdit.getText().toString(),phoneEdit.getText().toString(),addressEdit.getText().toString())){

                    dismiss();
                    try{
                        editUsertoDB();
                        //Toast.makeText(getContext(),"Successfully changed!", Toast.LENGTH_LONG);

                       // getActivity().finish();
                       // startActivity(getActivity().getIntent());
                    }catch (Exception e){
                        Toast.makeText(getContext(),"Something went wrong", Toast.LENGTH_LONG);
                    }finally {
                        dismiss();
                    }
                }
                break;
        }
    }

    boolean verifyInfo(String name, String lastName, String password, String phoneNumber, String address) {
        //if name is empty
        if (name.isEmpty()) {
            firstNameEdit.setError("Name is required");
            firstNameEdit.requestFocus();
            return false;
        }

        //validate name
        if (!name.toString().matches("[a-zA-Z]+")) {
            firstNameEdit.setError("Please enter a valid name");
            firstNameEdit.requestFocus();
            return false;
        }

        //if lastName is empty
        if (lastName.isEmpty()) {
            lastNameEdit.setError("Last name is required");
            lastNameEdit.requestFocus();
            return false;
        }

        //validate last name
        if (!lastName.toString().matches("[a-zA-Z]+")) {
            lastNameEdit.setError("Please enter a valid last name");
            lastNameEdit.requestFocus();
            return false;
        }

        //if password is empty
        if (password.isEmpty()) {
            passwordEdit.setError("Password is required");
            passwordEdit.requestFocus();
            return false;
        }

        //validate password
        if (password.length() < 6) {
            passwordEdit.setError("Minimum length of password should be 6");
            passwordEdit.requestFocus();
            return false;
        }

        //if phoneNumber is empty
        if (phoneNumber.isEmpty()) {
            phoneEdit.setError("Phone number is required");
            phoneEdit.requestFocus();
            return false;
        }

        //validate phoneNumber
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            phoneEdit.setError("Please enter a valid phone number");
            phoneEdit.requestFocus();
            return false;
        }

        //if address is empty
        if (address.isEmpty()) {
            addressEdit.setError("Address is required");
            addressEdit.requestFocus();
            return false;
        }

        //validate address
        if (!address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) {
            addressEdit.setError("Please enter a valid address");
            addressEdit.requestFocus();
            return false;
        }

        return true;
    }

    private void editUsertoDB() {
        //get inputs
        final String name = firstNameEdit.getText().toString().trim().toLowerCase();
        final String lastName = lastNameEdit.getText().toString().trim().toLowerCase();
        final String email = emailView.getText().toString().trim().toLowerCase();
        final String password = passwordEdit.getText().toString().trim();
        final String phoneNumber = phoneEdit.getText().toString().trim();
        final String address = addressEdit.getText().toString().trim().toLowerCase();
        Log.d("GREG", "|"+email);

        database.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Check if the dataSnapshot contains non-null value, if it does not then the username is available and we will
                //proceed to checking the user input and add the user to the db
                if (dataSnapshot.exists()) {

                    //this can probably be optimized, but this works lol
                    DataSnapshot ds = dataSnapshot.getChildren().iterator().next();



                    //load data in hash map
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("name",name);
                    temp.put("lastName", lastName);
                    temp.put("email", email);
                    temp.put("password",password);
                    temp.put("phoneNumber", phoneNumber);
                    temp.put("address", address);


                    //update children with hash map
                    ds.getRef().updateChildren(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}