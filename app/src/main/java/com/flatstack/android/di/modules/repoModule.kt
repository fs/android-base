package com.flatstack.android.di.modules

import com.flatstack.android.login.LoginRepository
import com.flatstack.android.profile.ProfileRepository
import com.flatstack.android.registration.PresignDataRepository
import com.flatstack.android.registration.RegistrationRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val repoModule = Kodein.Module(name = "repoModule") {
    bind<LoginRepository>() with provider { LoginRepository(instance(), instance(), instance(), instance()) }
    bind<ProfileRepository>() with provider { ProfileRepository(instance(), instance(), instance()) }
    bind<RegistrationRepository>() with provider { RegistrationRepository(instance(), instance(), instance()) }
    bind<PresignDataRepository>() with provider {
        PresignDataRepository(instance(), instance(), instance(), instance()) }
}
