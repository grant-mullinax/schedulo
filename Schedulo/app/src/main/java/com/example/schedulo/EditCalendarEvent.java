package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import com.github.sundeepk.compactcalendarview.domain.Event;

public class EditCalendarEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calendar_event);
    }

    // converts string date and time to epoch time
    public long converter(String date, String time, String toggle) {
        String month, day, year, hour, minute;
        month = date.substring(0, date.indexOf('/'));
        day = date.substring(date.indexOf('/') + 1, date.lastIndexOf('/'));
        year = date.substring(date.length() - 4);
        hour =  time.substring(0, time.indexOf(':'));
        minute = time.substring(time.length() - 2);

        int months, days, years, hours, minutes, toggler = 0;
        months = Integer.parseInt(month);
        days = Integer.parseInt(day);
        years = Integer.parseInt(year);
        years = years - 1970;
        hours = Integer.parseInt(hour);
        minutes = Integer.parseInt(minute);
        if (toggle.startsWith("P"))
            toggler += 12 * 3600;

        long epoch = (years * 31556926) + (months * 2629743) + (days * 86400)
                                        + (hours * 3600) + (minutes * 60) + toggler;

        return epoch;
    }

    // creates a new event object
    public void addEvent(View view) {
        // finds values input in each field on the UI
        EditText getName = findViewById(R.id.eventNameBox);
        EditText getDesc = findViewById(R.id.eventDescriptionBox);
        EditText getLoc = findViewById(R.id.eventLocationBox);
        EditText eventStartDateBox = findViewById(R.id.eventStartDateBox);
        EditText eventStartTimeBox = findViewById(R.id.eventStartTimeBox);
        EditText eventEndDateBox = findViewById(R.id.eventStartDateBox);
        EditText eventEndTimeBox = findViewById(R.id.eventStartTimeBox);
        ToggleButton toggleStart = findViewById(R.id.toggleButtonStart);
        ToggleButton toggleEnd = findViewById((R.id.toggleButtonEnd));
        String name = getName.getText().toString();

        String description = getDesc.getText().toString();
        String location = getLoc.getText().toString();
        String startDate = eventStartDateBox.getText().toString();
        String startTime = eventStartTimeBox.getText().toString();
        String endDate = eventEndDateBox.getText().toString();
        String endTime = eventEndTimeBox.getText().toString();
        String startToggle = toggleStart.toString();
        String endToggle = toggleEnd.toString();

        long unixStart, unixEnd;
        unixStart = converter(startDate, startTime, startToggle);
        unixEnd = converter(endDate, endTime, endToggle);

        Event event = new Event(Color.RED, unixStart, name);
        CalendarEvent calendarEvent = new CalendarEvent(name, description, location, unixStart, unixEnd);
        MainActivity.events.add(new CalendarEvent(name, description, location, unixStart, unixEnd));

        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.num++;
        startActivity(intent);
    }
}