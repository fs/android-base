package com.flatsoft.base.fragments;

import android.app.Fragment;

import com.flatsoft.base.App;
import com.flatsoft.base.R;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class MainFragment extends Fragment {
    @Override public void onStart() {
        super.onStart();
        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().setHomeButtonEnabled(false);
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
            getActivity().getActionBar().setTitle(R.string.app_name);
        }
        App.gaSendScreen(getActivity(), "Main Fragment");
    }
}
