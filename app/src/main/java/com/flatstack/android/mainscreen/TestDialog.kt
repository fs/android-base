package com.flatstack.android.mainscreen

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

import com.flatstack.android.R

class TestDialog : DialogFragment() {
    private lateinit var uiTitle: TextView
    internal lateinit var uiMessage: TextView

    private var title: String? = null
    private var message: String? = null

    private fun parseArguments() {
        arguments?.let {
            title = it.getString(KEY_TITLE)
            message = it.getString(KEY_MESSAGE)
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        parseArguments()
        uiTitle.text = title
        uiMessage.text = message
    }

    companion object {

        private const val KEY_TITLE = "dialogTitle"
        private const val KEY_MESSAGE = "dialogMessage"

        fun show(
            title: String?,
            message: String?,
            fm: FragmentManager
        ) {

            val dialog = TestDialog()

            val args = Bundle()
            args.putString(KEY_TITLE, title ?: "")
            args.putString(KEY_MESSAGE, message ?: "")
            dialog.arguments = args

            dialog.show(fm, TestDialog::class.java.name)
        }
    }
}
