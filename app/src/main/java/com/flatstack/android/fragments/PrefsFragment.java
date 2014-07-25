package com.flatstack.android.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.utils.ActionBars;
import com.flatstack.android.utils.MixPanel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PrefsFragment extends PreferenceFragment {
    @Inject @NotNull MixPanel mixPanel;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }

    @Override public void onStart() {
        super.onStart();
        Dagger.inject(this);
        ActionBars.configure(this, actionBar -> {
            ActionBars.homeAsUp(actionBar, true);
            actionBar.setTitle(R.string.settings);
        });
        mixPanel.track("screen", "name", getClass().getSimpleName());
    }
}
