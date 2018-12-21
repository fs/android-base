package com.flatstack.android.mainscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import butterknife.BindView

import com.bumptech.glide.Glide
import com.flatstack.android.R
import com.flatstack.android.SecondActivity
import com.flatstack.android.utils.ui.BaseFragment

class MainFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uiImage = view.findViewById<ImageView>(R.id.ivImage)
        uiImage.setOnClickListener {
            fragmentManager?.let {
                TestDialog.show("Example Hello", "Ublyudok, mat' tvoyu, a nu idi syuda, govno" +
                        " sobachye, reshil ko mne lezt'? Ti, zasranec vonyuchiy, mat' " +
                        "tvoyu, a?", it)
            }
        }

        view.findViewById<View>(R.id.btnButton).setOnClickListener {
            startActivity(Intent(activity, SecondActivity::class.java))
        }

        Glide.with(this)
                .load("https://pbs.twimg.com/profile_images/502109671600033792/QOAC0YGo.png")
                .into(uiImage)
    }
}
