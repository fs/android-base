package com.flatstack.android.di.modules

import com.flatstack.android.Router
import com.flatstack.android.login.LoginMapper
import com.flatstack.android.model.network.errors.ErrorHandler
import com.flatstack.android.util.StringResource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val appModule = Kodein.Module(name = "appModule") {
    bind<StringResource>() with singleton { StringResource(instance()) }
    bind<ErrorHandler>() with singleton { ErrorHandler(instance(), instance(), instance()) }
    bind<Router>() with singleton { Router(instance()) }
    bind<LoginMapper>() with singleton { LoginMapper() }
}
