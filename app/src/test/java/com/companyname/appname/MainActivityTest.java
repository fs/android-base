package com.companyname.appname;

import android.app.Fragment;
import android.app.FragmentManager;

import com.companyname.appname.dagger.Dagger;
import com.companyname.appname.fragments.MainFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.tester.android.view.TestMenuItem;
import org.robolectric.util.ActivityController;

import dagger.ObjectGraph;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    ActivityController<MainActivity> activityController;

    @Before public void setUp() throws Exception {
        activityController = Robolectric.buildActivity(MainActivity.class);
    }

    @Test public void testOnCreate() throws Exception {
        MainActivity mainActivity = activityController.create().get();
        assertNotNull(mainActivity.getObjectGraph());

        Fragment mainFragment = mainActivity.getFragmentManager().findFragmentById(android.R.id.content);
        assertNotNull(mainFragment);
        assertTrue(mainFragment instanceof MainFragment);
    }

    @Test public void testOnLaunch() throws Exception {
        MainActivity mainActivity = activityController.get();
        ObjectGraph objectGraph = Dagger.getObjectGraph(mainActivity);
        mainActivity.onLaunch();
        assertNotEquals(Dagger.getObjectGraph(mainActivity), objectGraph);
    }

    @Test public void testOnOptionsItemSelected() throws Exception {
        MainActivity mainActivity = activityController.create().get();
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, new Fragment())
                .addToBackStack(null)
                .commit();
        int oldStack = fragmentManager.getBackStackEntryCount();

        assertTrue(mainActivity.onOptionsItemSelected(new TestMenuItem(android.R.id.home)));
        assertTrue(fragmentManager.getBackStackEntryCount() < oldStack);
    }
}
