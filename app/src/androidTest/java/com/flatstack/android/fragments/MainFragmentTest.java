package com.flatstack.android.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import com.flatstack.android.R;
import com.flatstack.android.rx.RxActivity;
import com.flatstack.android.utils.Views;

import static org.assertj.core.api.Assertions.assertThat;

public class MainFragmentTest extends ActivityInstrumentationTestCase2<RxActivity> {
  MainFragment fragment;

  public MainFragmentTest() { super(RxActivity.class); }

  public void setUp() throws Exception {
    super.setUp();
    final FragmentManager fm = getActivity().getSupportFragmentManager();
    fm.beginTransaction()
        .add(android.R.id.content, new MainFragmentBuilder(android.R.id.content).build())
        .commit();
    getInstrumentation().runOnMainSync(fm::executePendingTransactions);
    fragment = (MainFragment) fm.findFragmentById(android.R.id.content);
  }

  public void testOnCreateView() throws Exception {
    final RxActivity activity = getActivity();
    assertThat(fragment.onCreateView(activity.getLayoutInflater(),
                                     (ViewGroup) Views.root(activity),
                                     null))
        .isNotNull();
  }

  public void testOnViewCreated() throws Exception {
    assertThat(fragment.picasso).isNotNull();
    assertThat(fragment.databaseHelper).isNotNull();
    final RxActivity activity = getActivity();
    assertThat(activity.getTitle()).isEqualTo(activity.getString(R.string.app_name));
    assertThat(fragment.hasOptionsMenu()).isTrue();
  }

  public void testOnOptionsItemSelected() throws Exception {
    fragment.onOptionsItemSelected(
        new ActionMenuItem(getActivity(), 0, R.id.action_settings, 0, 0, ""));
    final FragmentManager fm = getActivity().getSupportFragmentManager();
    getInstrumentation().runOnMainSync(fm::executePendingTransactions);
    assertThat(fm.findFragmentById(android.R.id.content))
        .isInstanceOf(PrefsFragment.class);
  }
}