package com.companyname.appname.dagger;

import android.os.Environment;

import com.companyname.appname.MainActivity;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class AppScopeDaggerModuleTest {
    AppScopeDaggerModule module;

    @Before public void setUp() throws Exception {
        Robolectric.buildActivity(MainActivity.class).create().get();
        module = new AppScopeDaggerModule(Robolectric.application);
    }

    @Test public void testProvideContext() throws Exception {
        assertEquals(Robolectric.application, module.provideContext());
    }

    @Test public void testProvideCacheDir() throws Exception {
        assertFalse(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()));
    }

    @Test public void testProvideOkHttpClient() throws Exception {
        assertNotSame(Robolectric.application.getClass(), MainActivity.class);
        OkHttpClient okHttpClient = Dagger.getObjectGraph(Robolectric.application).get(OkHttpClient.class);
        assertNotNull(okHttpClient.getResponseCache());
    }

    @Test public void testProvidePicasso() throws Exception {

    }
}
