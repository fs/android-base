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

import dagger.ObjectGraph;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * Created by adel on 6/7/14
 */
public abstract class ScopedFragment extends Fragment {
    @Nullable @Getter(AccessLevel.PROTECTED) Context     scopedContext;
    @Nullable                                ObjectGraph objectGraph;

    @NotNull protected abstract Object getDaggerModule(@NotNull Context activity);

    @NotNull protected abstract View onCreateScopedView(@NotNull LayoutInflater inflater,
                                                        @NotNull ViewGroup container,
                                                        @Nullable Bundle savedInstanceState);

    @NotNull ObjectGraph getObjectGraph(@NotNull Context activity) {
        if (objectGraph == null) {
            objectGraph = Dagger.getObjectGraph(activity).plus(getDaggerModule(activity));
        }
        return objectGraph;
    }

    @Override public void onAttach(@NotNull Activity activity) {
        super.onAttach(activity);
        scopedContext = new ScopedContextWrapper(activity, getObjectGraph(activity));
    }

    @Override public View onCreateView(@NotNull LayoutInflater inflater,
                                       @Nullable ViewGroup container,
                                       @Nullable Bundle savedInstanceState) {
        if (scopedContext == null || container == null) {
            throw new AssertionError("no nulls here");
        }
        return onCreateScopedView(LayoutInflater.from(scopedContext),
                                  container,
                                  savedInstanceState);
    }

    @Override public void onDetach() {
        scopedContext = null;
        super.onDetach();
    }
}
