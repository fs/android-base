package com.flatstack.android.util.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.flatstack.android.util.Keyboard

abstract class BaseActivity : AppCompatActivity() {

    abstract val screenConfig: ScreenConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(screenConfig.layoutRes)
        ButterKnife.bind(this)
        intent?.extras?.let { parseArguments(it) }
        when {
            screenConfig.titleRes != 0 -> setTitle(screenConfig.titleRes)
            screenConfig.title != null -> title = screenConfig.title
        }
        if (screenConfig.enableBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)
    }

    fun parseArguments(extras: Bundle) {}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (screenConfig.menuRes != 0) {
            val inflater = menuInflater
            inflater.inflate(screenConfig.menuRes, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home && screenConfig.enableBackButton) {
            Keyboard.hide(this)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
