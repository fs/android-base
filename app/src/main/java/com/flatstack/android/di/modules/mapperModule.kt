package com.flatstack.android.di.modules

import com.flatstack.android.registration.SessionFromRegistrationMapper
import com.flatstack.android.registration.mapper.PresignDataFromNetworkMapper
import com.flatstack.android.registration.mapper.PresignDataToNetworkMapper
import com.flatstack.android.registration.mapper.RegisterRequestToNetworkMapper
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val mapperModule = Kodein.Module(name = "mapperModule") {
    bind<SessionFromRegistrationMapper>() with provider { SessionFromRegistrationMapper() }
    bind<RegisterRequestToNetworkMapper>() with provider { RegisterRequestToNetworkMapper() }
    bind<PresignDataToNetworkMapper>() with provider { PresignDataToNetworkMapper() }
    bind<PresignDataFromNetworkMapper>() with provider { PresignDataFromNetworkMapper() }
}
