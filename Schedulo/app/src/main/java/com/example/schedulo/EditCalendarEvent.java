package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.sundeepk.compactcalendarview.domain.Event;

public class EditCalendarEvent extends AppCompatActivity {

    EditText eventNameBox = findViewById(R.id.eventNameBox);
    EditText eventLocationBox = findViewById(R.id.eventLocationBox);
    EditText eventStartDateBox = findViewById(R.id.eventStartDateBox);
    EditText eventStartTimeBox = findViewById(R.id.eventStartTimeBox);
    EditText eventEndDateBox = findViewById(R.id.eventStartDateBox);
    EditText eventEndTimeBox = findViewById(R.id.eventStartTimeBox);
    EditText eventDescriptionBox = findViewById(R.id.eventDescriptionBox);
    Button addEvent = findViewById(R.id.addEventButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calendar_event);

        addEvent.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view)
                    {
                        eventCreation();
                    }
                }
        );
    }

    public void eventCreation() {
        String name, location, startDate, startTime, endDate, endTime, description;

        name = eventNameBox.getText().toString();
        location = eventLocationBox.getText().toString();
        startDate = eventStartDateBox.getText().toString();
        startTime = eventStartTimeBox.getText().toString();
        endDate = eventEndDateBox.getText().toString();
        endTime = eventEndTimeBox.getText().toString();
        description = eventDescriptionBox.getText().toString();

        long unixStart, unixEnd;
        unixStart = converter(startDate, startTime);
        unixEnd = converter(endDate, endTime);

        Event event = new Event(Color.RED, unixStart, name);
        CalendarEvent calendarEvent = new CalendarEvent(name, description, location, unixStart, unixEnd);
    }

    public int converter(String date, String time) {
        return 0;
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
