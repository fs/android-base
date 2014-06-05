package com.flatsoft.base.dagger;

import android.content.Context;

import com.flatsoft.base.utils.Preferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import de.devland.esperandro.serialization.GsonSerializer;

@Module(addsTo = ApplicationScopeModule.class,
        library = true)
public class ActivityScopeModule {
    @Provides @Singleton Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides @Singleton Preferences providePreferences(Context context, Gson gson) {
        Esperandro.setSerializer(new GsonSerializer(gson));
        return Esperandro.getPreferences(Preferences.class, context);
    }
}
