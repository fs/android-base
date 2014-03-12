package com.companyname.appname.dagger;

import android.content.Context;

import com.companyname.appname.fragments.MainFragment;
import com.companyname.appname.qualifiers.IOThreadPool;
import com.companyname.appname.utils.JacksonSerializer;
import com.companyname.appname.utils.Preferences;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Module(addsTo = AppScopeDaggerModule.class,
        injects = {MainFragment.class})
public class UiScopeDaggerModule {
    @Provides @Singleton ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Provides @IOThreadPool Scheduler provideIOThreadPool() { return Schedulers.io(); }

    @Provides @Singleton JacksonSerializer provideSerializer(ObjectMapper objectMapper) {
        return new JacksonSerializer(objectMapper);
    }

    @Provides @Singleton
    Preferences providePreferences(Context context, JacksonSerializer jacksonSerializer) {
        Esperandro.setSerializer(jacksonSerializer);
        return Esperandro.getPreferences(Preferences.class, context);
    }
}
