package com.flatstack.android.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flatstack.android.App;
import com.flatstack.android.R;
import com.flatstack.android.dagger.components.DaggerMainComponent;
import com.flatstack.android.dagger.modules.MainModule;
import com.flatstack.android.rx.RxFragment;
import com.flatstack.android.utils.HomeAsUp;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Lazy;

public class MainFragment extends RxFragment {
    @Inject Lazy<Picasso>        picasso;

    @Arg @IdRes int container;

    @Bind(R.id.image) ImageView image;

    @Override public View onCreateView(LayoutInflater inflater,
                                       ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inject();
        activity().setTitle(R.string.app_name);
        setHasOptionsMenu(true);
        HomeAsUp.disable(activity());

        picasso.get()
            .load("https://pbs.twimg.com/profile_images/502109671600033792/QOAC0YGo.png")
            .fit()
            .into(image);
    }

    private void inject() {
        DaggerMainComponent.builder()
            .appComponent(App.component)
            .mainModule(new MainModule(this))
            .build()
            .inject(this);
    }

}
