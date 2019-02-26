package com.example.jla.campingguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivityDashboard extends AppCompatActivity {

    String EmailHolder;
    TextView Email;
    Button LogOUT, buttonThings,  buttonKnotTying, buttonFirstAid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard); Email = (TextView) findViewById(R.id.textView1);


        LogOUT = (Button) findViewById(R.id.buttonLogout);
        buttonThings = findViewById(R.id.buttonThings);
        buttonFirstAid = findViewById(R.id.buttonFirstAid);
        buttonKnotTying = findViewById(R.id.buttonKnotTying);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(MainActivity.UserEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+EmailHolder);

        buttonThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDashboard.this, ThingsToDo.class);
                startActivity(intent);
            }
        });

        buttonKnotTying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDashboard.this, KnotTying.class);
                startActivity(intent);
            }
        });

        buttonFirstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDashboard.this, FirstAid.class);
                startActivity(intent);
            }
        });

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

                Toast.makeText(MainActivityDashboard.this, "Log Out Successfully", Toast.LENGTH_LONG).show();


            }
        });

    }



}
