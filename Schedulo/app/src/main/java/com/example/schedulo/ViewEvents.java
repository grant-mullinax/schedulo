package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        CalendarEvent[] events = MainActivity.events.toArray(new CalendarEvent[MainActivity.events.size()]);
        ArrayAdapter<CalendarEvent> adapter = new ArrayAdapter<CalendarEvent>(this,
                android.R.layout.simple_list_item_1, events);
        ListView listView = (ListView) findViewById(R.id.eventList);
        listView.setAdapter(adapter);
    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
