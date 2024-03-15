package com.example.a4175project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
public class ReminderActivity extends AppCompatActivity {

    private EditText noteEditText;
    private TimePicker timePicker;
    private RecyclerView remindersRecyclerView;
    private ReminderAdapter reminderAdapter;
    private List<Reminder> reminderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        noteEditText = findViewById(R.id.noteEditText);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true); // Set time picker to use 24-hour format

        // Initialize RecyclerView
        remindersRecyclerView = findViewById(R.id.remindersRecyclerView);
        remindersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reminderList = new ArrayList<>();
        reminderAdapter = new ReminderAdapter(reminderList);
        remindersRecyclerView.setAdapter(reminderAdapter);
    }

    // Method to set a reminder with a note and time
    public void setReminder(View view) {
        String note = noteEditText.getText().toString().trim();
        if (note.isEmpty()) {
            Toast.makeText(this, "Please enter a note for the reminder", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the selected time from the TimePicker
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        // Create the new reminder
        Reminder newReminder = new Reminder(note, hour, minute);

        // Add the new reminder to the list
        reminderList.add(newReminder);

        // Notify the adapter that the data set has changed
        reminderAdapter.notifyDataSetChanged();

        // Scroll RecyclerView to the newly added item
        remindersRecyclerView.smoothScrollToPosition(reminderList.size() - 1);

        // Clear EditText
        noteEditText.getText().clear();

        Toast.makeText(this, "Reminder set successfully", Toast.LENGTH_SHORT).show();

    }
}

