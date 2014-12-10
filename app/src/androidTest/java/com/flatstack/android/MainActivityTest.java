package com.flatstack.android;

import android.test.ActivityInstrumentationTestCase2;
import com.flatstack.android.fragments.MainFragment;

import static org.assertj.core.api.Assertions.assertThat;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
  public MainActivityTest() { super(MainActivity.class); }

  public void setUp() throws Exception {
    super.setUp();
  }

  public void testOnCreate() throws Exception {
    final MainActivity activity = getActivity();
    assertThat(activity.persistence).isNotNull();
    assertThat(activity.getSupportActionBar()).isNotNull();
    assertThat(activity.getSupportFragmentManager().findFragmentById(R.id.content))
        .isNotNull()
        .isExactlyInstanceOf(MainFragment.class);
  }

  public void testGetObjectGraph() throws Exception {
    assertThat(getActivity().getObjectGraph()).isNotNull();
  }
}