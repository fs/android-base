package com.flatstack.android.util.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseDialogFragment : DialogFragment() {

    private var butterKnifeUnbinder: Unbinder? = null

    internal abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { parseArguments(it) }
    }

    protected open fun parseArguments(args: Bundle) {
        throw IllegalStateException("should be overridden")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(layoutRes, container, false)
        butterKnifeUnbinder = ButterKnife.bind(this, v)
        return v
    }

    override fun onDestroyView() {
        butterKnifeUnbinder?.unbind()
        super.onDestroyView()
    }

    companion object {
        protected fun <T : BaseDialogFragment> show(
            dialogFragment: T,
            activity: FragmentActivity
        ): T {
            val ft = activity.supportFragmentManager.beginTransaction()
            val prev = activity.supportFragmentManager
                    .findFragmentByTag(dialogFragment.javaClass.name)
            prev?.let {
                ft.remove(it)
                val df = prev as DialogFragment
                df.dismissAllowingStateLoss()
            }
            ft.addToBackStack(null)
            dialogFragment.show(ft, dialogFragment.javaClass.name)
            return dialogFragment
        }
    }
}
