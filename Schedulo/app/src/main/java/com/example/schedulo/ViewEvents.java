package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewEvents extends AppCompatActivity {

    private final String SERVER_URL = "http://10.0.2.2:7000/events";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        CalendarEvent[] events = setupEvents(MainActivity.getInstance().getEvents());
        CalendarAdapter adapter = new CalendarAdapter(this,
                android.R.layout.simple_list_item_1, events);
        ListView listView = (ListView) findViewById(R.id.eventList);
        listView.setAdapter(adapter);
    }

    public CalendarEvent[] setupEvents(List<CalendarEvent> events) {
        List<CalendarEvent> returns = new ArrayList<>();
        String prev = "";
        for(CalendarEvent e : events) {
            if(e.getStart() != -1 && e.getStartDate().compareTo(prev) != 0) {
                returns.add(new CalendarEvent(e.getStartDate(), "", "", -1, -1, null));
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

    public void deleteEvent(View view) {
        final CalendarEvent e = (CalendarEvent) view.getTag();
        final Context ctx = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Event");
        builder.setMessage("Are you sure you want to delete event " + e);
        builder.setCancelable(true);
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.getInstance().deleteEvent(e, ctx);
                        Intent intent = new Intent(ctx, MainActivity.class);
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
