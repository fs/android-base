package com.flatstack.android.fragments;

import android.os.Bundle;
import com.flatstack.android.R;
import com.flatstack.android.module.HomeAsUpModule;
import com.flatstack.android.rx.RxPreferenceFragment;

/**
 * Created by adelnizamutdinov on 10/12/14
 */
public class PrefsFragment extends RxPreferenceFragment {
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activity().setTitle(R.string.settings);
    addPreferencesFromResource(R.xml.prefs);
    addCallbackListener(new HomeAsUpModule(activity(), this));
  }
}
