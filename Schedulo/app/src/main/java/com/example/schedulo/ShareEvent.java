package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ShareEvent extends AppCompatActivity {
    private final String SERVER_URL = "http://10.0.2.2:7000/events";
    String name;
    String description;
    String location;
    String startTime;
    String endTime;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_event);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        description = intent.getStringExtra("description");
        location = intent.getStringExtra("location");
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");
        id = intent.getStringExtra("id");

        TextView nameView = findViewById(R.id.eventNameText);
        TextView locView = findViewById(R.id.eventLocText);
        TextView descView = findViewById(R.id.eventDescText);

        nameView.setText(name);
        locView.setText(location);
        descView.setText(description);
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

    public void sendEvent(View view) {
        TextView recipientBox = findViewById(R.id.recipientBox);
        String recipients = recipientBox.getText().toString();
        final StringTokenizer getRecipients = new StringTokenizer(recipients, ", ", false);
        while (getRecipients.hasMoreTokens()) {
            final String user = getRecipients.nextToken();
            String url = "http://10.0.2.2:7000/users/" + user + "/" + id;
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("\n\nSUCCESS\n\n" + response);
                            Intent intent = new Intent(ShareEvent.this, MainActivity.class);
                            ShareEvent.this.startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("\n\nERROR\n\n" + error.toString());
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", MainActivity.getInstance().getUsername());
                    params.put("password", MainActivity.getInstance().getPassword());

                    return params;
                }
            };
            queue.add(putRequest);
        }

        Intent intent = new Intent(ShareEvent.this, MainActivity.class);
        ShareEvent.this.startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(ShareEvent.this, EditCalendarEvent.class);
        ShareEvent.this.startActivity(intent);
    }

}


