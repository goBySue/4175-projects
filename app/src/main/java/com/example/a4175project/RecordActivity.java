package com.example.a4175project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // Retrieve the daily water consumption goal amount from the intent
        int goalAmount = getIntent().getIntExtra("GOAL_AMOUNT", 0); // 0 is the default value if the extra is not found

        // Log the goal amount to see if it's being retrieved correctly
        Log.d("RecordActivity", "Goal Amount: " + goalAmount);

        // Find the TextView to display the goal amount
        TextView goalAmountTextView = findViewById(R.id.goalAmountTextView);

        // Set the goal amount in the TextView
        goalAmountTextView.setText(goalAmount + " ml");
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity to prevent it from being stacked on top of the navigation stack
    }

}
