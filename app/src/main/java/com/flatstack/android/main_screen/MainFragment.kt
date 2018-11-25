package com.flatstack.android.main_screen

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

    @BindView(R.id.ivImage) internal lateinit var uiImage: ImageView
    @BindView(R.id.btnButton) internal lateinit var uiButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiImage.setOnClickListener {
            fragmentManager?.let {
                TestDialog.show("Example Hello", "Ublyudok, mat' tvoyu, a nu idi syuda, govno" +
                        " sobachye, reshil ko mne lezt'? Ti, zasranec vonyuchiy, mat' " +
                        "tvoyu, a?", it)
            }
        }

        uiButton.setOnClickListener {
            startActivity(Intent(activity, SecondActivity::class.java))
        }

        Glide.with(this)
                .load("https://pbs.twimg.com/profile_images/502109671600033792/QOAC0YGo.png")
                .into(uiImage)
    }
}
