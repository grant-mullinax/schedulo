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

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class EditCalendarEvent extends AppCompatActivity {
    static CalendarEvent event;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calendar_event);
        Intent intent = getIntent();
        if(event != null) {
            ((EditText) findViewById(R.id.eventNameBox)).setText(event.getName());
            ((EditText) findViewById(R.id.eventDescriptionBox)).setText(event.getDescription());
            ((EditText) findViewById(R.id.eventLocationBox)).setText(event.getLocation());
            ((EditText) findViewById(R.id.eventStartDateBox)).setText(event.getStartDate());
            ((EditText) findViewById(R.id.eventEndDateBox)).setText(event.getEndDate());
            ((EditText) findViewById(R.id.eventStartTimeBox)).setText(event.getStartTime());
            ((EditText) findViewById(R.id.eventEndTimeBox)).setText(event.getEndTime());
            ((Button) findViewById(R.id.addEventButton)).setText("Update Event");
            id = event.getId();
            System.out.println(id);
            Button shareButton = findViewById(R.id.sendButton);
            shareButton.setVisibility(View.VISIBLE);
        } else {
            id = null;
            try {
                ((EditText) findViewById(R.id.eventStartDateBox)).setText(intent.getStringExtra("date"));
                ((EditText) findViewById(R.id.eventEndDateBox)).setText(intent.getStringExtra("date"));
            } catch (Exception e) {

            }
        }
    }

    public static void setFields(CalendarEvent event2) {
        event = event2;
    }

    public long converter(String date, String time, String toggle) {
        Log.d("dsadsad", toggle);
        try {
            String month, day, year, hour, minute;
            month = date.substring(0, date.indexOf('/'));
            day = date.substring(date.indexOf('/') + 1, date.lastIndexOf('/'));
            year = date.substring(date.length() - 4);
            hour =  time.substring(0, time.indexOf(':'));
            minute = time.substring(time.indexOf(':')+1);
            return OffsetDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour) + (toggle.equals("PM") ? 12 : 0),
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
        EditText eventEndDateBox = findViewById(R.id.eventEndDateBox);
        EditText eventEndTimeBox = findViewById(R.id.eventEndTimeBox);
        ToggleButton startToggle = findViewById(R.id.toggleButtonStart);
        ToggleButton endToggle = findViewById(R.id.toggleButtonEnd);


        String name = getName.getText().toString();
        String description = getDesc.getText().toString();
        String location = getLoc.getText().toString();
        String startDate = eventStartDateBox.getText().toString();
        String startTime = eventStartTimeBox.getText().toString();
        String endDate = eventEndDateBox.getText().toString();
        String endTime = eventEndTimeBox.getText().toString();

        long unixStart, unixEnd;
        unixStart = converter(startDate, startTime, startToggle.getText().toString());
        unixEnd = converter(endDate, endTime, endToggle.getText().toString());

        Log.d("CREATATAT", startDate + " " + endDate);

        if(unixStart == -1 || unixEnd == -1) {
            return;
        }

        CalendarEvent calendarEvent = new CalendarEvent(name, description, location, unixStart, unixEnd, id);
        MainActivity.getInstance().addEvent(calendarEvent, this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendEvent(View view) {
        Intent intent = new Intent(EditCalendarEvent.this, ShareEvent.class);

        EditText getName = findViewById(R.id.eventNameBox);
        EditText getDesc = findViewById(R.id.eventDescriptionBox);
        EditText getLoc = findViewById(R.id.eventLocationBox);
        EditText eventStartDateBox = findViewById(R.id.eventStartDateBox);
        EditText eventStartTimeBox = findViewById(R.id.eventStartTimeBox);
        EditText eventEndDateBox = findViewById(R.id.eventStartDateBox);
        EditText eventEndTimeBox = findViewById(R.id.eventStartTimeBox);
        ToggleButton startToggle = findViewById(R.id.toggleButtonStart);
        ToggleButton endToggle = findViewById(R.id.toggleButtonEnd);

        String name;
        String description;
        String location;
        String startDate;
        String startTime;
        String endDate;
        String endTime;

        if (getName.getText() == null) {
            name = "Unnamed Event";
        } else {
            name = getName.getText().toString();
        }
        if (getDesc.getText() == null) {
            description = "";
        } else {
            description = getDesc.getText().toString();
        }
        if (getLoc.getText() == null) {
            location = "";
        } else {
            location = getLoc.getText().toString();
        }
        if (eventStartDateBox.getText() == null) {
            startDate = new Date().toString();
        } else {
            startDate = eventStartDateBox.getText().toString();
        }
        if (eventStartTimeBox.getText() == null) {
            startTime = "00:00";
        } else {
            startTime = eventStartTimeBox.getText().toString();
        }
        if (eventEndDateBox.getText() == null) {
            endDate = startDate;
        } else {
            endDate = eventEndDateBox.getText().toString();
        }
        if (eventEndTimeBox.getText() == null) {
            endTime = startTime;
        } else {
            endTime = eventEndTimeBox.getText().toString();
        }

        long unixStart, unixEnd;
        unixStart = converter(startDate, startTime, startToggle.getText().toString());
        unixEnd = converter(endDate, endTime, endToggle.getText().toString());

        Log.d("CREATATAT", startDate + " " + endDate);

        if(unixStart == -1 || unixEnd == -1) {
            return;
        }

        intent.putExtra("name", "" + name);
        intent.putExtra("description", "" + description);
        intent.putExtra("location", "" + location);
        intent.putExtra("startTime", "" + unixStart);
        intent.putExtra("endTime", "" + unixEnd);
        intent.putExtra("id", id);

        EditCalendarEvent.this.startActivity(intent);
    }
}
