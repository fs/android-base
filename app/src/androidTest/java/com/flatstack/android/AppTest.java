package com.flatstack.android;

import com.flatstack.android.dagger.Dagger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Config(emulateSdk = 18, manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class AppTest {
    @Test public void testOnCreate() throws Exception {
        App app = (App) Robolectric.application;
        assertNotNull(app.getObjectGraph());
        assertNotNull(Dagger.getObjectGraph(app));
    }

    @Test public void testAppClass() {
        assertEquals("TestApp", Robolectric.application.getClass().getSimpleName());
    }
}
