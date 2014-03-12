package com.companyname.appname.dagger;

import android.os.Environment;

import com.companyname.appname.MainActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class AppScopeDaggerModuleTest {
    AppScopeDaggerModule module;

    @Before public void setUp() throws Exception {
        module = new AppScopeDaggerModule(Robolectric.application);
    }
}
