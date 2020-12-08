package com.flatstack.android.di.modules

import com.apollographql.apollo.ApolloClient
import com.flatstack.android.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val netModule = Kodein.Module(name = "netModule") {
    bind<Interceptor>() with singleton { AuthorizationInterceptor(instance()) }

    bind<OkHttpClient>() with singleton { OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor(instance()))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build() }

    bind<ApolloClient>() with singleton {
        ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .okHttpClient(instance())
            .build()
    }
}
