package com.flatstack.android.utils.ui

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window

abstract class BaseDialogFragment : DialogFragment() {
    internal abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { parseArguments(it) }
        savedInstanceState?.let { restoreState(it) }
    }

    protected fun restoreState(savedState: Bundle) {}

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
    ) = inflater.inflate(layoutRes, container, false)

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
