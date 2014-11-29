package com.flatstack.android;

import com.flatstack.android.dagger.Dagger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class AppTest {
  @Test public void testOnCreate() throws Exception {
    App app = (App) Robolectric.application;
    assertThat(app.getObjectGraph()).isNotNull();
    assertThat(Dagger.getObjectGraph(app)).isNotNull();
  }

  @Test public void testAppClass() {
    assertThat(Robolectric.application.getClass().getSimpleName()).isEqualTo("TestApp");
  }
}
