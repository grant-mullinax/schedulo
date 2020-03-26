package com.example.schedulo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CalendarAdapter extends ArrayAdapter<CalendarEvent> {
    public CalendarAdapter(Context context, int resource, CalendarEvent[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private Context context;

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CalendarEvent item = super.getItem(position);

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        if(item.getStart() == -1) {
            row = inflater.inflate(R.layout.header, parent, false);
            TextView name = (TextView)row.findViewById(R.id.date);
            name.setText(item.getName());
            return row;
        }

        row = inflater.inflate(R.layout.list_item, parent, false);

        Button name = (Button)row.findViewById(R.id.go_to_view);
        Button delete = (Button)row.findViewById(R.id.delete_event);
        name.setText(item.getName());
        name.setTag(item);
        delete.setTag(item);
        return row;
    }
}
