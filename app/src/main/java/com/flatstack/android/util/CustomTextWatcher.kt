package com.flatstack.android.util

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData

class CustomTextWatcher(val liveData: MutableLiveData<String>) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        liveData.value = s.toString()
    }

    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
    }
}
