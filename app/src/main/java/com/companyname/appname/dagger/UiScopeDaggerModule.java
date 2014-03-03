package com.companyname.appname.dagger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    @Qualifier @Retention(RetentionPolicy.RUNTIME) public static @interface IOThreadPool {}

    @Provides @IOThreadPool Scheduler provideIOThreadPool() { return Schedulers.io(); }

    @Qualifier @Retention(RetentionPolicy.RUNTIME) public static @interface MainThread {}

    @Provides @MainThread Scheduler provideMainThread() { return AndroidSchedulers.mainThread(); }
}
