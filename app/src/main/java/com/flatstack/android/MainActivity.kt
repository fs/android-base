package com.flatstack.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flatstack.android.profile.AuthorizationModel
import kotlinx.coroutines.runBlocking
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val router by kodein.instance<Router>()
        val authorizationModel by kodein.instance<AuthorizationModel>()

        runBlocking {
            if (authorizationModel.isAuthorized()) {
                router.profile(this@MainActivity)
            } else {
                router.login(this@MainActivity)
            }
        }
        finish()
    }
}
