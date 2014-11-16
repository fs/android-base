package com.flatstack.android.dagger;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO dagger
//import dagger.ObjectGraph;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * Created by adel on 6/7/14
 */
public abstract class ScopedFragment extends Fragment {
    @NotNull @Getter(AccessLevel.PROTECTED) Context     scopedContext;
    // TODO dagger
//    @Nullable                               ObjectGraph objectGraph;

    @NotNull protected abstract View createScopedView(@NotNull LayoutInflater inflater,
                                                      @NotNull ViewGroup container,
                                                      @Nullable Bundle savedInstanceState);

    // TODO dagger
//    @NotNull protected abstract ObjectGraph createDaggerScope(@NotNull Context activity);

    @Override public void onAttach(@NotNull Activity activity) {
        super.onAttach(activity);
        // TODO dagger
//        scopedContext = new ScopedContextWrapper(activity, createDaggerScope(activity));
    }

    @Override public View onCreateView(@NotNull LayoutInflater inflater,
                                       @Nullable ViewGroup container,
                                       @Nullable Bundle savedInstanceState) {
        if (container == null) {
            throw new AssertionError("no nulls here");
        }
        return createScopedView(LayoutInflater.from(scopedContext),
                                container,
                                savedInstanceState);
    }

    @Override public void onDetach() {
        scopedContext = null;
        super.onDetach();
    }
}
