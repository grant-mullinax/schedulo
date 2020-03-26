package com.example.schedulo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static java.util.EnumSet.copyOf;


    public class MainActivity extends AppCompatActivity {

    private final String SERVER_URL = "http://10.0.2.2:7000/events";
    private static MainActivity instance = null;

    private List<CalendarEvent> events;
    static String username, password;

    CompactCalendarView compactCalendar;

    public MainActivity() { events = new ArrayList<>(); }

    public static void newInstance(String username, String password, Context ctx) {
        instance = new MainActivity();
        instance.username = username;
        instance.password = password;
        instance.pullEvents(ctx);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(null);

        compactCalendar =  (CompactCalendarView) findViewById(R.id.calendar);
        compactCalendar.setUseThreeLetterAbbreviation(true);

    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void addEvent(CalendarEvent event, Context ctx) {

        RequestQueue queue = Volley.newRequestQueue(ctx);

        JSONObject json = new JSONObject();
        try {
            json.put("name", event.getName());
            json.put("description", event.getDescription());
            json.put("location", event.getLocation());
            json.put("start", event.getStart());
            json.put("end", event.getEnd());
        } catch(Exception e) { events.add(new CalendarEvent(e.getMessage(), "", "", -1, -1, null)); return; }

        final CalendarEvent eventT = event;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SERVER_URL, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            eventT.setId(response.getString("id"));
                            events.add(eventT);
                        } catch(JSONException e) { }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    public void pullEvents(Context ctx) {
        events.clear();

        RequestQueue queue = Volley.newRequestQueue(ctx);
        events.add(new CalendarEvent("loading", "", "", -1, -1, null));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, SERVER_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        events.clear();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject event = response.getJSONObject(i);
                                events.add(new CalendarEvent(event.getString("name"), event.getString("description"),
                                        event.getString("location"), event.getLong("start"), event.getLong("end"), event.getString("id")));
                            }
                        } catch(Exception e) {
                            events.add(new CalendarEvent("error getting events", "", "", -1, -1, null));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        events.clear();
                        events.add(new CalendarEvent("error getting events", "", "", -1, -1, null));
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };
        queue.add(jsonArrayRequest);
    }

    public List<CalendarEvent> getEvents() {
        Collections.sort(events);
        return Collections.unmodifiableList(events);
    }

    public void AddCalendarEvent(View view) {
        Intent intent = new Intent(this, EditCalendarEvent.class);
        EditCalendarEvent.setFields(null);
        startActivity(intent);
    }

    public void LogOut(View view) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        instance = null;
        MainActivity.this.startActivity(intent);
    }

    public void ViewEvents(View view) {
        Intent intent = new Intent(MainActivity.this, ViewEvents.class);
        MainActivity.this.startActivity(intent);
    }
}
