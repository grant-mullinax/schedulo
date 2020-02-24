package com.example.schedulo;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalendarEventTest {

    @Test
    public void constructorTest() {
        CalendarEvent event = new CalendarEvent("test", "test desc", "test loc", 0, 1);
        assert(event.getName().equals("test"));
        assert(event.getDescription().equals("test desc"));
        assert(event.getLocation().equals("test loc"));
        assert(event.getStart() == 0);
        assert(event.getEnd() == 1x);
    }
}