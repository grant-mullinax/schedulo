package com.example.schedulo;

import android.content.Context;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Type;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ViewEventTest {
    private String username, password;

    @Rule
    public ActivityTestRule<Login> activityRule
            = new ActivityTestRule<>(Login.class);

    @Before
    public void initCredentials() {
        username = "1234";
        password = "4321";
    }

    @Test
    public void viewEventTest() {
        onView(withId(R.id.number))
                .perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login))
                .perform(click());
        onView(withId(R.id.eventList)).perform(click());
        onView(withId(R.id.back)).check(matches(isDisplayed()));
    }

    @Test
    public void clickEventTest() {
        onView(withId(R.id.eventList)).perform(click());
        onView(withId(R.id.go_to_view)).perform(click());
        onView(withId(R.id.addEventButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addEventButton)).perform(click());
        onView(withId(R.id.eventNameBox)).check(matches(withText("test")));
    }
}
