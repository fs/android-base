package com.flatstack.android.main_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flatstack.android.R;
import com.flatstack.android.utils.HomeAsUp;
import com.flatstack.android.utils.ui.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainFragment extends BaseFragment {

    @Bind(R.id.image) ImageView image;

    @Override public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override public View onCreateView(LayoutInflater inflater,
                                       ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.app_name);
        setHasOptionsMenu(true);
        HomeAsUp.disable((AppCompatActivity) getActivity());

        // just show that lambdas work
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
