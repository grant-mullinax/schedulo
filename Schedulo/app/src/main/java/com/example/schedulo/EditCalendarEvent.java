package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditCalendarEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calendar_event);
    }

    public void addEvent(View view) {
        EditText getName = findViewById(R.id.eventNameBox);
        EditText getDesc = findViewById(R.id.eventDescriptionBox);
        EditText getLoc = findViewById(R.id.eventLocationBox);
        String Name = getName.getText().toString();
        String Desc = getDesc.getText().toString();
        String Loc = getLoc.getText().toString();
        MainActivity.events.add(new CalendarEvent(Name, Desc, Loc, 0, 0));
        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.num++;
        startActivity(intent);
    }
}