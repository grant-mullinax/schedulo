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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calendar_event);

    }

    public int converter(String date, String time) {
        return 0;
    }

    public void addEvent(View view) {
        EditText getName = findViewById(R.id.eventNameBox);
        EditText getDesc = findViewById(R.id.eventDescriptionBox);
        EditText getLoc = findViewById(R.id.eventLocationBox);
        EditText eventStartDateBox = findViewById(R.id.eventStartDateBox);
        EditText eventStartTimeBox = findViewById(R.id.eventStartTimeBox);
        EditText eventEndDateBox = findViewById(R.id.eventStartDateBox);
        EditText eventEndTimeBox = findViewById(R.id.eventStartTimeBox);
        String name = getName.getText().toString();

        String description = getDesc.getText().toString();
        String location = getLoc.getText().toString();
        String startDate = eventStartDateBox.getText().toString();
        String startTime = eventStartTimeBox.getText().toString();
        String endDate = eventEndDateBox.getText().toString();
        String endTime = eventEndTimeBox.getText().toString();

        long unixStart, unixEnd;
        unixStart = converter(startDate, startTime);
        unixEnd = converter(endDate, endTime);

        Event event = new Event(Color.RED, unixStart, name);
        CalendarEvent calendarEvent = new CalendarEvent(name, description, location, unixStart, unixEnd);
        MainActivity.events.add(new CalendarEvent(name, description, location, unixStart, unixEnd));
        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.num++;
        startActivity(intent);
    }
}