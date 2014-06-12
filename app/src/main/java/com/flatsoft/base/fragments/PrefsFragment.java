package com.flatsoft.base.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.flatsoft.base.R;
import com.flatsoft.base.dagger.Dagger;
import com.flatsoft.base.utils.ActionBars;
import com.flatsoft.base.utils.MixPanel;

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
