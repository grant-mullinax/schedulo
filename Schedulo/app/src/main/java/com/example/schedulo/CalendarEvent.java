package com.example.schedulo;

import java.time.Instant;

class CalendarEvent implements Comparable<CalendarEvent> {
    private String name, description, location, startDate, endDate, startTime, endTime;
    private long start, end;

    public CalendarEvent (String name, String description, String location, long start, long end) {
        this.name = name;
        this. description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        String iso = Instant.ofEpochSecond(start).toString();
        startDate = iso.substring(0, iso.indexOf("T"));
        startTime = iso.substring(iso.indexOf("T")+1, iso.indexOf("Z"));
        iso = Instant.ofEpochSecond(end).toString();
        endDate = iso.substring(0, iso.indexOf("T"));
        endTime = iso.substring(iso.indexOf("T")+1, iso.indexOf("Z"));
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDescription(String description) {
        this. description = description;
    }
    public void setStart(long start) {
        this.start = start;
    }
    public void setEnd(long end) {
        this.end = end;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public long getStart() { return start; }
    public long getEnd() { return end; }
    public String getStartDate() { return startDate; }
    public String getStartTime() { return startTime; }
    public String getEndDate() { return endDate; }
    public String getEndTime() { return endTime; }

    public String toString() {
        return this.name;
    }

    public int compareTo(CalendarEvent o) {
        if(o.start != start) return Long.compare(start, o.start);
        return name.compareTo(o.name);
    }
}
