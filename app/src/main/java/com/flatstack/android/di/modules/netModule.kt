package com.flatstack.android.di.modules

import com.flatstack.android.BuildConfig
import com.flatstack.android.login.entities.LoginRequest
import com.flatstack.android.login.entities.LoginResponse
import com.flatstack.android.model.entities.Session
import com.flatstack.android.model.network.ApiResponse
import com.flatstack.android.model.network.ApiSuccessResponse
import com.flatstack.android.model.network.IApi
import com.flatstack.android.model.network.adapter.CoroutineCallAdapterFactory
import com.flatstack.android.profile.entities.Book
import com.flatstack.android.profile.entities.Profile
import com.flatstack.android.profile.entities.ProfileResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.with
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = Kodein.Module(name = "netModule") {
    constant("baseUrl") with BuildConfig.BASE_URL

    bind<Gson>() with singleton {
        GsonBuilder().create()
    }
    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
            .build()
    }
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl(instance<String>("baseUrl"))
            .addConverterFactory(GsonConverterFactory.create(instance()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(instance())
            .build()
    }
    bind<IApi>() with singleton {
        //        instance<Retrofit>().create(IApi::class.java)
        object : IApi {
            override fun loadUserAsync(token: String): Deferred<ApiResponse<ProfileResponse>> =
                CompletableDeferred<ApiResponse<ProfileResponse>>(
                    ApiSuccessResponse(ProfileResponse(profile))
                )

            override fun loginAsync(loginRequest: LoginRequest): Deferred<ApiResponse<LoginResponse>> =
                CompletableDeferred<ApiResponse<LoginResponse>>(
                    ApiSuccessResponse(LoginResponse(session))
                )
        }
    }
}

@Suppress("MagicNumber")
val profile = Profile(1, "username", null).also { profile ->
    profile.favoriteBook = Book(7, "Favorite title", 77)
    profile.booksRead = ArrayList<Book>().also {
        it.add(Book(1, "Title 1", 10))
        it.add(Book(2, "Title 2", 20))
        it.add(Book(3, "Title 3", 30))
    }
}

val session = Session(1, "access_token")
