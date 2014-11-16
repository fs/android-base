package com.flatstack.android.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.ScopedFragment;
import com.flatstack.android.dagger.modules.MainFragmentScopeModule;
import com.flatstack.android.utils.ActionBars;
import com.flatstack.android.utils.DatabaseHelper;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import dagger.Lazy;
// TODO dagger
//import dagger.ObjectGraph;
import rx.subjects.Subject;

public class MainFragment extends ScopedFragment {
    @Inject @NotNull Lazy<Picasso>                   picasso; // application scope
    @Inject @NotNull Lazy<DatabaseHelper>            databaseHelper; //activity scope
    @Inject @NotNull Lazy<Subject<Boolean, Boolean>> truth; //fragment scope

    // TODO dagger
//    @NotNull @Override protected ObjectGraph createDaggerScope(@NotNull Context activity) {
//        return Dagger.getObjectGraph(getActivity()).plus(new MainFragmentScopeModule());
//    }

    @NotNull @Override protected View createScopedView(@NotNull LayoutInflater inflater,
                                                       @NotNull ViewGroup container,
                                                       @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBars.configure(this, actionBar -> {
            ActionBars.homeAsUp(actionBar, false);
            actionBar.setTitle(R.string.app_name);
        });
        Dagger.inject(this);
    }
}
