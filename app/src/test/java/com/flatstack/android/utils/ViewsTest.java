package com.flatstack.android.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.flatstack.android.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 18, constants = BuildConfig.class, packageName = "com.flatstack.android")
public class ViewsTest {
  @Test public void testRoot() throws Exception {
    final Activity activity = Robolectric.buildActivity(Activity.class).create().get();
    assertThat(Views.root(activity))
        .isNotNull()
        .hasId(android.R.id.content);
  }

  @Test public void testSetHeight() throws Exception {
    final View view = new View(RuntimeEnvironment.application);
    view.setLayoutParams(new ViewGroup.LayoutParams(25, 50));
    assertThat(view.getLayoutParams()).hasHeight(50);

    Views.setHeight(view, 500);
    assertThat(view.getLayoutParams()).hasHeight(500);
  }
}