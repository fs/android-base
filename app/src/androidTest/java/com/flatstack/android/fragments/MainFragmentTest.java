package com.flatstack.android.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.view.ViewGroup;
import com.flatstack.android.R;
import com.flatstack.android.rx.RxActivity;
import com.flatstack.android.utils.Views;

import static org.assertj.core.api.Assertions.assertThat;

public class MainFragmentTest extends FragmentTestCase<MainFragment> {
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
    executePendingTransactions();
    assertThat(fm.findFragmentById(android.R.id.content))
        .isInstanceOf(PrefsFragment.class);
  }

  @Override protected MainFragment create() {
    return new MainFragmentBuilder(android.R.id.content).build();
  }
}