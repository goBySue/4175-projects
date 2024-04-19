package com.example.a4175project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a4175project.Reminder;

import java.util.ArrayList;

public class ReminderAdapter extends ArrayAdapter<Reminder> {

    public ReminderAdapter(Context context, ArrayList<Reminder> reminders) {
        super(context, 0, reminders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reminder reminder = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(android.R.id.text1);
        TextView textViewDescription = convertView.findViewById(android.R.id.text2);

        textViewTitle.setText(reminder.getTitle());
        textViewDescription.setText(reminder.getDescription());

        return convertView;
    }
}
