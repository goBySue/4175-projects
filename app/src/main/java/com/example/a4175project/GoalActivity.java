package com.example.a4175project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class GoalActivity extends AppCompatActivity {

    private int goalAmount = 2000; // Default goal amount
    private TextView amountTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        // Initialize TextView
        amountTextView = findViewById(R.id.amountTextView);
        updateGoalAmountDisplay();

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // Retrieve the saved goal amount, or use the default value if not available
        goalAmount = sharedPreferences.getInt("goalAmount", 2000);

        // Set click listener for the Save button
        Button saveGoalBtn = findViewById(R.id.saveGoalBtn);
        saveGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGoal();
            }
        });

        // Set click listener for the go to Record button
        Button goToRecordBtn = findViewById(R.id.toRecordBtn);
        goToRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRecord();
            }
        });
    }

    // Method to handle increasing the goal amount
    // Method to handle increasing the goal amount
    public void increaseGoal(View view) {
        goalAmount += 100; // Increase by 100ml, you can change this value as needed
        updateGoalAmountDisplay();
    }


    // Method to handle decreasing the goal amount
    public void decreaseGoal(View view) {
        if (goalAmount >= 100) { // Ensure the goal amount doesn't go below 0
            goalAmount -= 100; // Decrease by 100ml, you can change this value as needed
            updateGoalAmountDisplay();
        }
    }

    // Method to update the displayed goal amount
    private void updateGoalAmountDisplay() {
        amountTextView.setText(goalAmount + " ml");
    }

    // Method to save the goal amount
    private void saveGoal() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("goalAmount", goalAmount);
        editor.apply();
        Toast.makeText(this, "Goal amount saved", Toast.LENGTH_SHORT).show();
    }

    // Method to handle the Back to Menu button click event
    private void goToRecord() {
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra("GOAL_AMOUNT", goalAmount);
        startActivity(intent);
        finish(); // Optional: Finish the current activity to prevent it from being stacked on top of the navigation stack
    }

}
