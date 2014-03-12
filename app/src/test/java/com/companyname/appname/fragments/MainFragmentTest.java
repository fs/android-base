package com.companyname.appname.fragments;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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
}
