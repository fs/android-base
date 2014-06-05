package com.flatsoft.base.fragments;

import android.app.Fragment;

import com.flatsoft.base.R;
import com.flatsoft.base.dagger.Dagger;
import com.flatsoft.base.utils.ActionBars;
import com.flatsoft.base.utils.MixPanel;

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
