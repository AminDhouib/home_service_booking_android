package com.handy.agile.agile_app.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.handy.agile.agile_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take me to the login activity
                Intent loginActivityIntent = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(loginActivityIntent);
            }
        });
    }

}
