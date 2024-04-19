package com.example.a4175project;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {

    private ArrayList<Reminder> reminders;
    private ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        reminders = new ArrayList<>();
        adapter = new ReminderAdapter(this, reminders);

        ListView listViewTasks = findViewById(R.id.listViewTasks);
        listViewTasks.setAdapter(adapter);

        // Handle click on a reminder
        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAddTaskDialog(position);
            }
        });

        Button buttonAddReminder = findViewById(R.id.buttonAddReminder);
        buttonAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextReminderTitle = findViewById(R.id.editTextReminderTitle);
                EditText editTextReminderDescription = findViewById(R.id.editTextReminderDescription);

                String title = editTextReminderTitle.getText().toString().trim();
                String description = editTextReminderDescription.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    addReminder(title, description);
                    editTextReminderTitle.setText("");
                    editTextReminderDescription.setText("");
                }
            }
        });
    }

    private void addReminder(String title, String description) {
        Reminder newReminder = new Reminder(title, description);
        reminders.add(newReminder);
        adapter.notifyDataSetChanged();
    }

    private void showAddTaskDialog(final int reminderPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReminderActivity.this);
        builder.setTitle("Add Task");

        // Set up the input
        final EditText input = new EditText(ReminderActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String task = input.getText().toString().trim();
                if (!task.isEmpty()) {
                    if (reminderPosition != -1 && reminderPosition < reminders.size()) {
                        Reminder selectedReminder = reminders.get(reminderPosition);
                        selectedReminder.addTask(task);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}