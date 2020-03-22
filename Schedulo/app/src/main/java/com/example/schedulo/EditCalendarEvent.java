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

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class EditCalendarEvent extends AppCompatActivity {
    static CalendarEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calendar_event);
        if(event != null) {
            ((EditText) findViewById(R.id.eventNameBox)).setText(event.getName());
            ((EditText) findViewById(R.id.eventDescriptionBox)).setText(event.getDescription());
            ((EditText) findViewById(R.id.eventLocationBox)).setText(event.getLocation());
            ((EditText) findViewById(R.id.eventStartDateBox)).setText(event.getStartDate());
            ((EditText) findViewById(R.id.eventEndDateBox)).setText(event.getEndDate());
            ((EditText) findViewById(R.id.eventStartTimeBox)).setText(event.getStartTime());
            ((EditText) findViewById(R.id.eventEndTimeBox)).setText(event.getEndTime());
        }
    }

    public static void setFields(CalendarEvent event2) {
        event = event2;
    }

    public long converter(String date, String time) {
        try {
            String month, day, year, hour, minute;
            month = date.substring(0, date.indexOf('/'));
            day = date.substring(date.indexOf('/') + 1, date.lastIndexOf('/'));
            year = date.substring(date.length() - 4);
            hour =  time.substring(0, time.indexOf(':'));
            minute = time.substring(time.indexOf(':')+1);
            return OffsetDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour),
                    Integer.parseInt(minute), 0, 0, ZoneOffset.UTC).toEpochSecond();
        } catch(Exception e) {
            return -1;
        }
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

        if(unixStart == -1 || unixEnd == -1) {
            return;
        }

        Event event = new Event(Color.RED, unixStart, name);
        CalendarEvent calendarEvent = new CalendarEvent(name, description, location, unixStart, unixEnd);
        MainActivity.getInstance().addEvent(calendarEvent);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}