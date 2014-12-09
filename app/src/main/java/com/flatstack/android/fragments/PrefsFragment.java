package com.flatstack.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatstack.android.R;
import com.flatstack.android.utils.ActionBars;

public class PrefsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.prefs, container, false);
    }

    @Override public void onStart() {
        super.onStart();
        ActionBars.configure(this, actionBar -> {
            ActionBars.homeAsUp(actionBar, true);
            actionBar.setTitle(R.string.settings);
        });
    }
}
