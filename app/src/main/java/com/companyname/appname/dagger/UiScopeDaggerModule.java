package com.companyname.appname.dagger;

import com.companyname.appname.qualifiers.IOThreadPool;
import com.companyname.appname.qualifiers.MainThread;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Provides @IOThreadPool Scheduler provideIOThreadPool() { return Schedulers.io(); }

    @Provides @MainThread Scheduler provideMainThread() { return AndroidSchedulers.mainThread(); }
}
