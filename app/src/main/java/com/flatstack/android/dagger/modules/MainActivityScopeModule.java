package com.flatstack.android.dagger.modules;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatstack.android.MainActivity;
import com.flatstack.android.utils.DatabaseHelper;
import com.flatstack.android.utils.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import de.devland.esperandro.serialization.JacksonSerializer;

@Module(addsTo = ApplicationScopeModule.class,
        injects = {
                MainActivity.class
        },
        library = true)
public class MainActivityScopeModule {
    @Provides @Singleton ObjectMapper provideJackson() { return new ObjectMapper(); }

    @Provides @Singleton Preferences providePreferences(Context context,
                                                        ObjectMapper objectMapper) {
        Esperandro.setSerializer(new JacksonSerializer(objectMapper));
        return Esperandro.getPreferences(Preferences.class, context);
    }

    @Provides @Singleton DatabaseHelper provideDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }
}
