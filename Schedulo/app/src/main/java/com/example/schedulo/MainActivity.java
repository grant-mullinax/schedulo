package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AddCalendarEvent(View view) {
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calendar);
        long selectedDate = (simpleCalendarView.getDate()) / 1000;
        Intent intent = new Intent(this, EditCalendarEvent.class);
        startActivity(intent);
    }

    public void LogOut(View view) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        MainActivity.this.startActivity(intent);
    }
}
