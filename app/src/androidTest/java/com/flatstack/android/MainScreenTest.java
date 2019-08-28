package com.flatstack.android;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainScreenTest {

    @Rule
    public ScreenshotActivityRule<MainActivity> testRule =
            new ScreenshotActivityRule<>(MainActivity.class);

    @Test
    public void whenAppLaunch_androidBaseVisible() throws Exception {
        onView(allOf(withId(R.id.title), withText(R.string.app_name)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void whenButtonClick_startedActivity() {
        onView(withId(R.id.button)).perform(click());
        intended(hasComponent(SecondActivity.class.getName()));
    }
}