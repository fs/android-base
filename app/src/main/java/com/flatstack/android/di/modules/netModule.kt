package com.flatstack.android.di.modules

import com.apollographql.apollo.ApolloClient
import com.flatstack.android.BuildConfig
import com.flatstack.android.model.network.NetworkManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.*

val netModule = Kodein.Module(name = "netModule") {
    bind<Interceptor>() with singleton { AuthorizationInterceptor(instance()) }

    bind<OkHttpClient>() with singleton { OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .addInterceptor(instance<Interceptor>())
        .build() }

    bind<ApolloClient>() with singleton {
        ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .okHttpClient(instance())
            .build()
    }

    bind<OkHttpClient>("restClient") with singleton { OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()
    }

    bind<NetworkManager>() with provider { NetworkManager(instance("restClient")) }
}
