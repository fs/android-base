package com.flatsoft.base.dagger;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatsoft.base.utils.JacksonSerializer;
import com.flatsoft.base.utils.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Module(addsTo = AppScopeDaggerModule.class,
        library = true)
public class UiScopeDaggerModule {
    @Provides @Singleton ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Provides @Singleton JacksonSerializer provideSerializer(ObjectMapper objectMapper) {
        return new JacksonSerializer(objectMapper);
    }

    @Provides @Singleton
    Preferences providePreferences(Context context, JacksonSerializer jacksonSerializer) {
        Esperandro.setSerializer(jacksonSerializer);
        return Esperandro.getPreferences(Preferences.class, context);
    }
}
