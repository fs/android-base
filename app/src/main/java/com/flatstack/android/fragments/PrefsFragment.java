package com.flatstack.android.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.utils.ActionBars;

public class PrefsFragment extends PreferenceFragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }

    @Override public void onStart() {
        super.onStart();
        ActionBars.configure(this, actionBar -> {
            ActionBars.homeAsUp(actionBar, true);
            actionBar.setTitle(R.string.settings);
        });
    }
}
