package com.flatstack.android.util.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseFragment : Fragment() {

    private var butterKnifeUnbinder: Unbinder? = null

    @get:LayoutRes abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(layoutRes, container, false)
        butterKnifeUnbinder = ButterKnife.bind(this, rootView)
        return rootView
    }

    override fun onDestroyView() {
        butterKnifeUnbinder?.unbind()
        super.onDestroyView()
    }
}
