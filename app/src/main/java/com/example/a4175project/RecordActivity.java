package com.example.a4175project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    private int goalAmount; // To store the daily water consumption goal amount
    private int currentAmount = 0; // To store the current water consumption amount
    private TextView goalAmountTextView;
    private TextView currentAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // Retrieve the daily water consumption goal amount from the intent
        goalAmount = getIntent().getIntExtra("GOAL_AMOUNT", 0); // 0 is the default value if the extra is not found

        // Find the TextView to display the goal amount
        goalAmountTextView = findViewById(R.id.goalAmountTextView);
        goalAmountTextView.setText(goalAmount + " ml");

        // Find the TextView to display the current amount
        currentAmountTextView = findViewById(R.id.currentAmountTextView);
        updateCurrentAmountDisplay();
    }

    // Method to handle adding 1 cup (250ml) of water
    public void addCup(View view) {
        Log.d("AddCup", "addCup method called");
        // Increase the current amount by 250ml
        currentAmount += 250;
        Log.d("AddCup", "Current amount: " + currentAmount);

        // Update the current amount TextView to reflect the current amount
        updateCurrentAmountDisplay();

        // Check if the jar is filled
        if (currentAmount >= goalAmount) {
            // Jar is filled, perform actions accordingly (e.g., display a message, change color, etc.)
            jarFilled();
        }
    }

    // Method to update the displayed current amount
    private void updateCurrentAmountDisplay() {
        currentAmountTextView.setText("Current amount: " +currentAmount + " ml");
    }

    // Method to perform actions when the jar is filled
    private void jarFilled() {
        // Display a message or perform other actions
        // For example:
        // Toast.makeText(this, "Congratulations! You have reached your daily water consumption goal.", Toast.LENGTH_SHORT).show();
    }

    // Method to handle the Back to Menu button click event
    public void backToMenu(View view) {
        finish(); // Finish the activity to go back to the previous screen
    }
}
