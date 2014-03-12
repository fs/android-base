package com.flatsoft.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.flatsoft.base.dagger.Dagger;
import com.flatsoft.base.dagger.UiScopeDaggerModule;
import com.flatsoft.base.fragments.MainFragment;
import com.flatsoft.base.fragments.PrefsFragment;
import com.github.mttkay.memento.Memento;
import com.github.mttkay.memento.MementoCallbacks;
import com.github.mttkay.memento.Retain;

import org.jetbrains.annotations.NotNull;

import dagger.ObjectGraph;
import lombok.Getter;

public class MainActivity extends Activity implements MementoCallbacks {
    @Retain @NotNull @Getter ObjectGraph objectGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Memento.retain(this);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, new MainFragment())
                    .commit();
        }
    }

    @Override public void onLaunch() {
        objectGraph = Dagger.getAppScope(this).plus(new UiScopeDaggerModule());
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
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

//    @Override public void onStart() {
//        super.onStart();
//        EasyTracker.getInstance(this).activityStart(this);
//    }
//
//    @Override public void onStop() {
//        super.onStop();
//        EasyTracker.getInstance(this).activityStop(this);
//    }
}