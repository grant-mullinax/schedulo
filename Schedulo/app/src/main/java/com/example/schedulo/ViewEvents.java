package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        CalendarEvent[] events = setupEvents(MainActivity.events);
        CalendarAdapter adapter = new CalendarAdapter(this,
                android.R.layout.simple_list_item_1, events);
        ListView listView = (ListView) findViewById(R.id.eventList);
        listView.setAdapter(adapter);
    }

    public CalendarEvent[] setupEvents(List<CalendarEvent> events) {
        Collections.sort(events);
        List<CalendarEvent> returns = new ArrayList<>();
        String prev = "";
        for(CalendarEvent e : events) {
            if(e.getStartDate().compareTo(prev) != 0) {
                returns.add(new CalendarEvent(e.getStartDate(), "", "", -1, -1));
                prev = e.getStartDate();
            }
            returns.add(e);
        }
        return returns.toArray(new CalendarEvent[returns.size()]);
    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void editEvent(View view) {
        Intent intent = new Intent(this, EditCalendarEvent.class);
        EditCalendarEvent.setFields((CalendarEvent) view.getTag());
        startActivity(intent);
    }
}
