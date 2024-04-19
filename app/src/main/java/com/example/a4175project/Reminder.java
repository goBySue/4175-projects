package com.example.a4175project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Reminder {
    private String title;
    private String description;
    private ArrayList<ToDoItem> tasks;

    public Reminder(String title, String description) {
        this.title = title;
        this.description = description;
        this.tasks = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<ToDoItem> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        tasks.add(new ToDoItem(task));
    }
}