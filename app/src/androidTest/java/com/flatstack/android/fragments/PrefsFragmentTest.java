package com.flatstack.android.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.internal.view.menu.ActionMenuItem;
import com.flatstack.android.R;
import com.flatstack.android.rx.RxActivity;

import static org.assertj.core.api.Assertions.assertThat;


public class PrefsFragmentTest extends FragmentTestCase<PrefsFragment> {
  @Override protected PrefsFragment create() {
    return new PrefsFragment();
  }

  public void testOnCreate() throws Exception {
    final RxActivity activity = getActivity();
    assertThat(activity.getTitle()).isEqualTo(activity.getString(R.string.settings));
    assertThat(fragment.hasOptionsMenu()).isTrue();
  }

  public void testBack() {
    final FragmentManager fm = getActivity().getSupportFragmentManager();
    fm.beginTransaction()
        .replace(android.R.id.content, create())
        .addToBackStack(null)
        .commit();
    executePendingTransactions();
    assertThat(fm.getBackStackEntryCount()).isEqualTo(1);

    fragment.onOptionsItemSelected(
        new ActionMenuItem(getActivity(), 0, android.R.id.home, 0, 0, ""));
    executePendingTransactions();
    assertThat(fm.getBackStackEntryCount()).isEqualTo(0);
  }
}