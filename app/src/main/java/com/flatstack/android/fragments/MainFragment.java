package com.flatstack.android.fragments;

import android.app.Fragment;

import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.utils.ActionBars;
import com.flatstack.android.utils.MixPanel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class MainFragment extends Fragment {
    @Inject @NotNull MixPanel mixPanel;

    @Override public void onStart() {
        super.onStart();
        ActionBars.configure(this, actionBar -> {
            ActionBars.homeAsUp(actionBar, false);
            actionBar.setTitle(R.string.app_name);
        });
        Dagger.inject(this);
        mixPanel.track("screen", "name", getClass().getSimpleName());
    }
}
