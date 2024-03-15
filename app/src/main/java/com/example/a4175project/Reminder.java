package com.example.a4175project;

public class Reminder {
    private String note;
    private String time;

    public Reminder(String note, String time) {
        this.note = note;
        this.time = time;
    }

    public Reminder(String note, int hour, int minute) {
    }

    public String getNote() {
        return note;
    }

    public String getTime() {
        return time;
    }
}
