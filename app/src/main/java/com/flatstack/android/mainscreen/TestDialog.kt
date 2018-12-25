package com.flatstack.android.mainscreen

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.TextView

import com.flatstack.android.R
import com.flatstack.android.utils.ui.BaseDialogFragment

class TestDialog : BaseDialogFragment() {
    private var title: String? = null
    private var message: String? = null

    override val layoutRes: Int
        get() = R.layout.dialog_test

    override fun parseArguments(args: Bundle) {
        arguments?.let {
            title = it.getString(KEY_TITLE)
            message = it.getString(KEY_MESSAGE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.dialog_title).text = title
        view.findViewById<TextView>(R.id.dialog_message).text = message
    }

    companion object {

        private const val KEY_TITLE = "dialogTitle"
        private const val KEY_MESSAGE = "dialogMessage"

        fun show(
                title: String?,
                message: String?,
                fm: FragmentManager
        ) {
            val dialog = TestDialog().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title ?: "")
                    putString(KEY_MESSAGE, message ?: "")
                }
            }

            dialog.show(fm, TestDialog::class.java.name)
        }
    }
}
