package com.flatstack.android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.utils.ActionBars;

public class MainFragment extends Fragment {
    @Override public void onStart() {
        super.onStart();
        ActionBars.configure(this, actionBar -> {
            ActionBars.homeAsUp(actionBar, false);
            actionBar.setTitle(R.string.app_name);
        });
    }

    @Override public View onCreateView(LayoutInflater inflater,
                                       ViewGroup container,
                                       Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main, container, false);
    }
}
