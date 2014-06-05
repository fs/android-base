package com.flatsoft.base.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.functions.Action1;

import static org.fest.assertions.api.Assertions.assertThat;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ActionBarsTest {
    Activity activity;

    @Before public void setUp() {
        activity = Robolectric.buildActivity(Activity.class)
                .create()
                .get();
    }

    @Test public void testConfigure() throws Exception {
        Fragment fragment = new Fragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(fragment, "")
                .commit();
        ActionBars.configure(fragment, new Action1<ActionBar>() {
            @Override public void call(ActionBar actionBar) {
                actionBar.setTitle("test");
            }
        });
        assertThat(activity.getActionBar().getTitle()).isEqualTo("test");
    }

    @Test public void testConfigure1() throws Exception {
        ActionBars.configure(activity, new Action1<ActionBar>() {
            @Override public void call(ActionBar actionBar) {
                actionBar.setSubtitle("fuck");
            }
        });
        assertThat(activity.getActionBar().getSubtitle()).isEqualTo("fuck");
    }
}