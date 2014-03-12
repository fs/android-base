package com.flatsoft.base.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.flatsoft.base.App;
import com.flatsoft.base.R;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class PrefsFragment extends PreferenceFragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }

    @Override public void onStart() {
        super.onStart();
        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            getActivity().getActionBar().setTitle(R.string.settings);
        }
        App.gaSendScreen(getActivity(), "Preferences");
    }
}
