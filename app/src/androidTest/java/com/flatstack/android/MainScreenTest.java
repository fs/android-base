package com.flatstack.android;

import android.support.test.runner.AndroidJUnit4;

import com.flatstack.android.main_screen.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by ereminilya on 6/4/17.
 */

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
}