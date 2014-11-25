package com.flatstack.android.utils;

import android.app.Activity;
import android.app.Fragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

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
    ActionBars.configure(fragment, actionBar -> actionBar.setTitle("test"));
    assertThat(activity.getActionBar().getTitle()).isEqualTo("test");
  }

  @Test public void testConfigure1() throws Exception {
    ActionBars.configure(activity, actionBar -> actionBar.setSubtitle("fuck"));
    assertThat(activity.getActionBar().getSubtitle()).isEqualTo("fuck");
  }
}