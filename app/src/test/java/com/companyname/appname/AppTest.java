package com.companyname.appname;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class AppTest {
    @Test public void testOnCreate() throws Exception {
        App app = (App) Robolectric.application;
        Assert.assertNotNull(app.getObjectGraph());
    }
}
