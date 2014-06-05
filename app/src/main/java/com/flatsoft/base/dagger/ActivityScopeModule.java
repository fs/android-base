package com.flatsoft.base.dagger;

import android.content.Context;

import com.flatsoft.base.utils.DatabaseHelper;
import com.flatsoft.base.utils.Preferences;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import de.devland.esperandro.serialization.GsonSerializer;

@Module(addsTo = ApplicationScopeModule.class,
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
