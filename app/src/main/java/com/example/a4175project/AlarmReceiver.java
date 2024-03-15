package com.example.a4175project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String note = intent.getStringExtra("NOTE"); // Correct key is "NOTE"
        if (note != null) {
            Toast.makeText(context, "Time to drink water!\nNote: " + note, Toast.LENGTH_LONG).show();
        }

    }
}
