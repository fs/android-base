package com.flatstack.android.di.modules

import androidx.lifecycle.ViewModelProvider
import com.flatstack.android.login.LoginViewModel
import com.flatstack.android.profile.ProfileViewModel
import com.flatstack.android.util.ViewModelFactory
import com.flatstack.android.util.bindViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val viewModelModule = Kodein.Module(name = "viewModelModule") {
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(dkodein) }

    bindViewModel<LoginViewModel>() with provider { LoginViewModel(instance(), instance()) }
    bindViewModel<ProfileViewModel>() with provider { ProfileViewModel(instance(), instance()) }
}
