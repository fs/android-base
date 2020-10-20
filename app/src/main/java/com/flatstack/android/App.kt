package com.flatstack.android

import android.app.Application
import com.flatstack.android.di.initKodein
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.kodein.di.KodeinAware

class App : Application(), KodeinAware {
    override val kodein = initKodein(this)

    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}
