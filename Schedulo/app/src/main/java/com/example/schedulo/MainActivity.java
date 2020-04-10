package com.example.schedulo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


    public class MainActivity extends AppCompatActivity {

    private final String SERVER_URL = "http://10.0.2.2:7000/events";
    private static MainActivity instance = null;

    private List<CalendarEvent> events;
    private String username, password, returnId;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
    private SimpleDateFormat formatSelected = new SimpleDateFormat("MM/dd/yyyy");
    private TextView month;
    private boolean visible = true;
    private Date selectedDate;

    private CompactCalendarView compactCalendar, base;
    private ImageButton prev;
    private Button add;

    private DrawerLayout sidebarLayout;
    private ActionBarDrawerToggle sidebarToggle;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

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

        prev = findViewById(R.id.prevbutton);
        add = findViewById(R.id.add);

        sidebarLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        sidebarToggle = new ActionBarDrawerToggle(this, sidebarLayout, R.string.open, R.string.close) {
             @Override
             public void onDrawerSlide(View drawerView, float slideOffset) {
                 super.onDrawerSlide(drawerView, slideOffset);
                 if (visible) {
                     compactCalendar.setVisibility(View.INVISIBLE);
                     prev.setVisibility(View.INVISIBLE);
                     add.setVisibility(View.INVISIBLE);
                 } else {
                     compactCalendar.setVisibility(View.VISIBLE);
                     prev.setVisibility(View.VISIBLE);
                     add.setVisibility(View.VISIBLE);
                 }
             }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                visible = true;
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                visible = false;
            }
         };
        sidebarLayout.addDrawerListener(sidebarToggle);
        sidebarToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        compactCalendar =  (CompactCalendarView) findViewById(R.id.calendar);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
        base = findViewById(R.id.calendar2);
        base.setUseThreeLetterAbbreviation(true);
        base.setFirstDayOfWeek(Calendar.SUNDAY);

        month = findViewById(R.id.monthname);
        month.setText(dateFormatForMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));
        selectedDate = new Date();
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                selectedDate = dateClicked;
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                if (selectedDate.before(firstDayOfNewMonth))
                {
                    base.scrollRight();
                } else {
                    base.scrollLeft();
                }
                selectedDate = firstDayOfNewMonth;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(sidebarToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void deleteEvent(CalendarEvent event, Context ctx) {
        final Context ctx2 = ctx;
        RequestQueue queue = Volley.newRequestQueue(ctx);
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, SERVER_URL+"/"+event.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MainActivity.getInstance().pullEvents(ctx2);
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
        queue.add(deleteRequest);
    }

    public String addEvent(CalendarEvent event, Context ctx) {
        final Context ctx2 = ctx;
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JSONObject json = new JSONObject();
        try {
            json.put("name", event.getName());
            json.put("description", event.getDescription());
            json.put("location", event.getLocation());
            json.put("start", event.getStart());
            json.put("end", event.getEnd());
        } catch(Exception e) { events.add(new CalendarEvent(e.getMessage(), "", "", -1, -1, null)); return null; }

        final CalendarEvent eventT = event;

        String url = SERVER_URL;
        int type = Request.Method.POST;
        if(event.getId() != null) {
            url += "/" + event.getId();
            Log.d("dsadsad", event.getId());
            type = Request.Method.PUT;
        }
        Log.d("dsadsad", json.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(type, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        MainActivity.getInstance().pullEvents(ctx2);
                        try {
                            returnId = response.getString("id");
                        } catch (Exception e) {
                            returnId = null;
                        }
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

        return returnId;
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
        intent.putExtra("date", formatSelected.format(selectedDate));
        startActivity(intent);
    }

    public void LogOut(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        instance = null;
        MainActivity.this.startActivity(intent);
    }

    public void ViewEvents(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, ViewEvents.class);
        MainActivity.this.startActivity(intent);
    }

    public void prev(View view) {
        compactCalendar.scrollLeft();
    }

    public void next(View view) {
        compactCalendar.scrollRight();
    }
}
