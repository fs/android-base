package com.flatstack.android.main_screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flatstack.android.R;
import com.flatstack.android.utils.ui.BaseFragment;

import butterknife.Bind;

public class MainFragment extends BaseFragment {

    @Bind(R.id.image) ImageView image;

    @Override public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image.setOnClickListener(listener ->
            TestDialog.show("Example Hello", "Ublyudok, mat' tvoyu, a nu idi syuda, " +
                    "govno sobachye, reshil ko mne lezt'? Ti, zasranec vonyuchiy, mat' tvoyu, a?",
                getFragmentManager())
        );

        Glide.with(this)
            .load("https://pbs.twimg.com/profile_images/502109671600033792/QOAC0YGo.png")
            .into(image);
    }
}
