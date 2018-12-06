package com.flatstack.android.mainscreen

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
        when {
            Intents.isActivityExpandedFromLauncherIcon(this) -> finish()
            savedInstanceState == null -> showMainFragment()
        }
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, MainFragment())
            .commit()
    }
}
