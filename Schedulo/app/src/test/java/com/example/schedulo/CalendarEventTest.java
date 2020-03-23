package com.example.schedulo;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarEventTest {

    @Test
    public void constructorTest() {
        CalendarEvent event = new CalendarEvent("test", "test desc", "test loc", 0, 1);
        assert(event.getName().equals("test"));
        assert(event.getDescription().equals("test desc"));
        assert(event.getLocation().equals("test loc"));
        assert(event.getStart() == 0);
        assert(event.getEnd() == 1);
        assert(event.toString().equals("test"));
        assert(event.getStartDate().equals("01/01/1970"));
        assert(event.getEndDate().equals("01/01/1970"));
    }
}