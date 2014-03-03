package com.companyname.appname;

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
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class AppTest {
    @Test public void testOnCreate() throws Exception {
        App app = (App) Robolectric.application;
        assertNotNull(app.getObjectGraph());
    }

    @Test public void testGetModules() throws Exception {
        App app = (App) Robolectric.application;
        assertEquals(2, app.getModules().size());
    }

    @Test public void testAppClass() {
        assertEquals("TestApp", Robolectric.application.getClass().getSimpleName());
    }
}
