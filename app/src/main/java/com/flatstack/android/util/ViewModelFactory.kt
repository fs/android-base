package com.flatstack.android.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DKodein
import org.kodein.di.generic.instanceOrNull

class ViewModelFactory(private val injector: DKodein) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        injector.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T? ?: modelClass.newInstance()
}
