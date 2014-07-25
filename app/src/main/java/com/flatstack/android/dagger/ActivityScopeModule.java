package com.flatstack.android.dagger;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatstack.android.fragments.MainFragment;
import com.flatstack.android.fragments.PrefsFragment;
import com.flatstack.android.utils.DatabaseHelper;
import com.flatstack.android.utils.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import de.devland.esperandro.serialization.JacksonSerializer;

@Module(addsTo = ApplicationScopeModule.class,
        injects = {MainFragment.class, PrefsFragment.class},
        library = true)
public class ActivityScopeModule {
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
