package com.flatsoft.base;

import android.app.Application;
import android.content.Context;

import com.flatsoft.base.dagger.AppScopeDaggerModule;
import com.flatsoft.base.utils.TimberCrashReportingTree;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import lombok.Getter;
import timber.log.Timber;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class App extends Application {
    @NotNull @Getter ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
//        Crittercism.initialize(getApplicationContext(), getString(R.string.crittercism_app_id));
        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new TimberCrashReportingTree());
        objectGraph = ObjectGraph.create(getModules().toArray());
    }

    // we create a list containing the MyModule Dagger module
    // later we can add any other modules to it (for testing)
    @NotNull protected List<Object> getModules() {
        return new ArrayList<>(Arrays.asList(new AppScopeDaggerModule(this)));
    }

    public static void gaSendScreen(Context context, String screenName) {
        EasyTracker tracker = EasyTracker.getInstance(context);
        tracker.set(Fields.SCREEN_NAME, screenName);
        tracker.send(MapBuilder.createAppView().build());
    }
}
