package com.flatstack.android;

import android.test.ApplicationTestCase;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adelnizamutdinov on 10/12/14
 */
public class AppTest extends ApplicationTestCase<App> {
  public AppTest() { super(App.class); }

  @Override protected void setUp() throws Exception {
    super.setUp();
    createApplication();
  }

  public void testCreate() {
    assertThat(getApplication().getObjectGraph()).isNotNull();
  }

  public void testModules() {
    assertThat(getApplication().getDaggerModules()).hasSize(1);
  }
}
