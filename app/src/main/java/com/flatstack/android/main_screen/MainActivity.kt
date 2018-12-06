package com.flatstack.android.main_screen

import android.os.Bundle
import com.flatstack.android.R
import com.flatstack.android.utils.Intents
import com.flatstack.android.utils.ui.BaseActivity
import com.flatstack.android.utils.ui.ScreenConfig

class MainActivity : BaseActivity() {
    override val screenConfig: ScreenConfig
        get() = ScreenConfig(R.layout.activity_main, titleRes = R.string.app_name, enableBackButton = true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Intents.isActivityExpandedFromLauncherIcon(this)) {
            finish()
        } else if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content, MainFragment())
                    .commit()
        }
    }
}