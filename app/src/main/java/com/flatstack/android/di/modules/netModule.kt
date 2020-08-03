package com.flatstack.android.di.modules

import com.apollographql.apollo.ApolloClient
import com.flatstack.android.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.with

val netModule = Kodein.Module(name = "netModule") {
    constant("baseUrl") with BuildConfig.GQL_URL

    bind<Interceptor>() with singleton { AuthorizationInterceptor(instance()) }

    bind<OkHttpClient>() with singleton { OkHttpClient.Builder()
        .addInterceptor(instance())
        .build() }

    bind<ApolloClient>() with singleton {
        ApolloClient.builder()
            .serverUrl(instance<String>("baseUrl"))
            .okHttpClient(instance())
            .build()
    }
}
