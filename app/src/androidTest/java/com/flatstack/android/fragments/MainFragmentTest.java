package com.flatstack.android.fragments;

import android.app.Application;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.flatstack.android.R;
import com.flatstack.android.rx.RxActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.tester.android.view.TestMenuItem;

import static org.assertj.core.api.Assertions.assertThat;


@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainFragmentTest {
  MainFragment fragment;

  @Before public void setUp() throws Exception {
    final FragmentManager fm = Robolectric.buildActivity(RxActivity.class)
        .create()
        .get()
        .getSupportFragmentManager();
    fragment = new MainFragmentBuilder(android.R.id.content).build();
    fm.beginTransaction()
        .add(android.R.id.content, fragment)
        .commit();
  }

  @Test public void testOnCreateView() throws Exception {
    assertThat(fragment.onCreateView(LayoutInflater.from(Robolectric.application),
                                     new FrameLayout(Robolectric.application),
                                     null))
        .isNotNull();
  }

  @Test public void testOnViewCreated() throws Exception {
    final Application ctx = Robolectric.application;
    fragment.onViewCreated(new View(ctx), null);
    assertThat(fragment.getActivity().getTitle())
        .isEqualTo(ctx.getString(R.string.app_name));
    assertThat(fragment.hasOptionsMenu()).isTrue();
  }

  @Test public void testOnOptionsItemSelected() throws Exception {
    fragment.onOptionsItemSelected(new TestMenuItem(R.id.action_settings));
    assertThat(fragment.getActivity().getSupportFragmentManager()
                   .findFragmentById(android.R.id.content))
        .isExactlyInstanceOf(PrefsFragment.class);
  }
}