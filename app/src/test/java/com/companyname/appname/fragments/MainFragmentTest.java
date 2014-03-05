package com.companyname.appname.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.companyname.appname.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.tester.android.view.TestMenu;
import org.robolectric.tester.android.view.TestMenuItem;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by adelnizamutdinov on 05/03/2014
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainFragmentTest {
    Activity     activity;
    MainFragment mainFragment;

    @Before public void setUp() throws Exception {
        activity = Robolectric.buildActivity(Activity.class)
                .create()
                .get();
        mainFragment = new MainFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, mainFragment)
                .commit();
    }

    @Test public void testOnCreate() throws Exception {
        // can't test onCreate, fuck android
    }

    @Test public void testOnCreateOptionsMenu() throws Exception {
        TestMenu menu = new TestMenu(activity);
        MenuInflater inflater = new MenuInflater(activity);
        mainFragment.onCreateOptionsMenu(menu, inflater);
        assertNotNull(menu.findItem(R.id.action_settings));
    }

    @Test public void testOnCreateView() throws Exception {
        LayoutInflater inflater = LayoutInflater.from(activity);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);

        View view = mainFragment.onCreateView(inflater, viewGroup, null);
        assertThat(activity.getActionBar())
                .hasTitle(activity.getString(R.string.app_name))
                .hasDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE |
                        ActionBar.DISPLAY_SHOW_HOME |
                        ActionBar.DISPLAY_USE_LOGO);
        assertThat(view).isExactlyInstanceOf(FrameLayout.class);
        FrameLayout frameLayout = (FrameLayout) view;
        assertThat(frameLayout.getChildAt(0)).isExactlyInstanceOf(TextView.class);
    }

    @Test public void testOnOptionsItemSelected() throws Exception {
        try {
            mainFragment.onOptionsItemSelected(new TestMenuItem(R.id.action_settings));
        } catch (InflateException e) {
            // robolectric not implemented PrefsFragment
        }
    }
}
