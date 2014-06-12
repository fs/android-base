package com.flatstack.android.dagger;

import android.content.Context;

import com.flatstack.android.fragments.MainFragment;
import com.flatstack.android.fragments.PrefsFragment;
import com.flatstack.android.utils.DatabaseHelper;
import com.flatstack.android.utils.Preferences;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import de.devland.esperandro.serialization.GsonSerializer;

@Module(addsTo = ApplicationScopeModule.class,
        injects = {MainFragment.class, PrefsFragment.class},
        library = true)
public class ActivityScopeModule {
    @Provides @Singleton Gson provideGson() {
        return new Gson();
    }

    @Provides @Singleton Preferences providePreferences(Context context, Gson gson) {
        Esperandro.setSerializer(new GsonSerializer(gson));
        return Esperandro.getPreferences(Preferences.class, context);
    }

    @Provides @Singleton DatabaseHelper provideDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }
}
