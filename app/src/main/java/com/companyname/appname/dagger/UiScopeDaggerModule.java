package com.companyname.appname.dagger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Module(addsTo = AppScopeDaggerModule.class, library = true)
public class UiScopeDaggerModule {
    @Provides @Singleton ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
