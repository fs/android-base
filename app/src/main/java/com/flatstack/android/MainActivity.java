package com.flatstack.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.Injector;
import com.flatstack.android.dagger.modules.MainActivityScopeModule;
import com.flatstack.android.fragments.MainFragment;
import com.flatstack.android.fragments.PrefsFragment;
import com.flatstack.android.utils.Preferences;
import com.github.mttkay.memento.Memento;
import com.github.mttkay.memento.MementoCallbacks;
import com.github.mttkay.memento.Retain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import dagger.ObjectGraph;
import lombok.Getter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity implements MementoCallbacks, Injector {
    static {
        CalligraphyConfig.initDefault(null);
    }

    @Inject @NotNull Preferences preferences; // injected from activity scope

    @Retain @NotNull @Getter ObjectGraph objectGraph;

    @Override protected void attachBaseContext(@NotNull Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }

    @Override public void onAttachFragment(Fragment fragment) {
        Memento.retain(this);
        super.onAttachFragment(fragment);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Memento.retain(this);
        Dagger.inject(this);
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(android.R.id.content) == null) {
            fm.beginTransaction()
                    .add(android.R.id.content, new MainFragment())
                    .commit();
        }
    }

    @Override public void onLaunch() {
        objectGraph = Dagger.getObjectGraph(getApplication())
                .plus(new MainActivityScopeModule());
    }

    @Override public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;

            case R.id.action_settings:
                getFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new PrefsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}