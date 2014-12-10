package com.flatstack.android.fragments;

import dagger.Module;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class MainFragmentTest {
  @Module static class MyModule {}
}