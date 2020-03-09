package com.example.schedulo;

import org.junit.BeforeClass;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void converterTest() {
        String date, time, toggle;
        date = "3/14/2020";
        time = "10:00";
        toggle = "PM";

        EditCalendarEvent editCalendarEvent = new EditCalendarEvent();
        assert (editCalendarEvent.converter(date, time, toggle) == 1584223200);

        date = "1/1/1970";
        time = "1:00";
        toggle = "AM";
        assert (editCalendarEvent.converter(date, time, toggle) == 3600);

        date = "4/30/2020";
        time = "8:20";
        toggle = "AM";
        assert (editCalendarEvent.converter(date, time, toggle) == 1588234800);
    }
}
