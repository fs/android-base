package com.flatstack.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.ScopedContextWrapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import dagger.Module;
import dagger.ObjectGraph;

import static org.fest.assertions.api.Assertions.assertThat;

@Config(emulateSdk = 18, manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class MainFragmentTest {
    @Module static class MyModule {}

    @Test public void testInjectorContextWrapper() {
        Robolectric.buildActivity(Activity.class).create().get();
        ObjectGraph objectGraph =
                Dagger.getObjectGraph(Robolectric.application).plus(new MyModule());
        Context context = new ScopedContextWrapper(Robolectric.application, objectGraph);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main, new FrameLayout(context));
        assertThat(Dagger.getObjectGraph(view.getContext())).isEqualTo(objectGraph);
    }
}