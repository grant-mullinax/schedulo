package com.example.schedulo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static public List<CalendarEvent> events = new ArrayList<CalendarEvent>();;
    static int num = 0;

    CompactCalendarView compactCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(null);

        compactCalendar =  (CompactCalendarView) findViewById(R.id.compactcalendar);
        compactCalendar.setUseThreeLetterAbbreviation(true);

    }

    public void AddCalendarEvent(View view) {
        Intent intent = new Intent(this, EditCalendarEvent.class);
        startActivity(intent);
    }

    public void LogOut(View view) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        MainActivity.this.startActivity(intent);
    }

    public void ViewEvents(View view) {
        Intent intent = new Intent(MainActivity.this, ViewEvents.class);
        MainActivity.this.startActivity(intent);
    }
}
