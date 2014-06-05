package com.flatsoft.base.fragments;

import android.app.Fragment;

import com.flatsoft.base.R;
import com.flatsoft.base.utils.ActionBars;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class MainFragment extends Fragment {
    @Override public void onStart() {
        super.onStart();
        ActionBars.configure(this, actionBar -> {
            ActionBars.homeAsUp(actionBar, false);
            actionBar.setTitle(R.string.app_name);
        });
    }
}
