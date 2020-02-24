package com.example.schedulo;

class CalendarEvent {
    private String name, description, location;
    private long start, end;

    public CalendarEvent (String name, String description, String location, long start, long end) {
        this.name = name;
        this. description = description;
        this.location = location;
        this.start = start;
        this.end = end;
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
}
