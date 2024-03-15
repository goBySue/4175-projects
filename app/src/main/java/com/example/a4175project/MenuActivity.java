package com.example.a4175project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    ImageButton goalBtn;
    ImageButton recordBtn;
    ImageButton alarmBtn;
    ImageButton searchBtn;
    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize the ImageButtons after setting the content view
        goalBtn = findViewById(R.id.goalBtn);
        recordBtn = findViewById(R.id.recordBtn);
        alarmBtn = findViewById(R.id.reminderBtn);
        searchBtn = findViewById(R.id.searchBtn);
        welcomeTextView = findViewById(R.id.welcomeSign);

        // Retrieve username from intent extras
        String username = getIntent().getStringExtra("USERNAME");

        // Update the welcome message with the username
        welcomeTextView.setText("Hi, " + username);

        // Set click listeners for each ImageView
        goalBtn.setOnClickListener(view -> {
            // Start the GoalActivity when the Goal image is clicked
            startActivity(new Intent(MenuActivity.this, GoalActivity.class));
        });

        recordBtn.setOnClickListener(view -> {
            // Start the RecordActivity when the Record image is clicked
            startActivity(new Intent(MenuActivity.this, RecordActivity.class));
        });

        alarmBtn.setOnClickListener(view -> {
            // Start the ReminderActivity when the Alarm image is clicked
            startActivity(new Intent(MenuActivity.this, ReminderActivity.class));
        });

        searchBtn.setOnClickListener(view -> {
            // Start the NutritionActivity when the Search image is clicked
            startActivity(new Intent(MenuActivity.this, NutritionActivity.class));
        });


    }


}