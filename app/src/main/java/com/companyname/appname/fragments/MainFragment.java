package com.companyname.appname.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.companyname.appname.R;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class MainFragment extends Fragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_fragment, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().setHomeButtonEnabled(false);
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
            getActivity().getActionBar().setTitle(R.string.app_name);
        }
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction()
                            .replace(android.R.id.content, new PrefsFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
