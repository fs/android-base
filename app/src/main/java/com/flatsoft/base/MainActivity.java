package com.flatsoft.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.flatsoft.base.dagger.ActivityScopeModule;
import com.flatsoft.base.dagger.Dagger;
import com.flatsoft.base.dagger.Injector;
import com.flatsoft.base.fragments.MainFragment;
import com.flatsoft.base.fragments.PrefsFragment;
import com.github.mttkay.memento.Memento;
import com.github.mttkay.memento.MementoCallbacks;
import com.github.mttkay.memento.Retain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dagger.ObjectGraph;
import lombok.Getter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity implements MementoCallbacks, Injector {
    static {
        CalligraphyConfig.initDefault(null);
    }

    @Retain @NotNull @Getter ObjectGraph objectGraph;

    @Override protected void attachBaseContext(@NotNull Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Memento.retain(this);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, new MainFragment())
                    .commit();
        }
    }

    @Override public void onLaunch() {
        objectGraph = Dagger.getObjectGraph(getApplication())
                .plus(new ActivityScopeModule());
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