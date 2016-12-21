package com.flatstack.android.utils.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @LayoutRes public abstract int getLayoutRes();

    @Override public void onCreate(@Nullable Bundle savedState) {
        super.onCreate(savedState);
        if (savedState != null) {
            restoreState(savedState);
        }
    }

    public void restoreState(@NonNull Bundle savedState) {
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    protected Context context() {
        return getContext();
    }
}
