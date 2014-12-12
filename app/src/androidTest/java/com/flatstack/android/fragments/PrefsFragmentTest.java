package com.flatstack.android.fragments;

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
}