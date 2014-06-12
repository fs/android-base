package com.flatstack.android.dagger;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dagger.ObjectGraph;
import lombok.Getter;

/**
 * Created by adel on 6/7/14
 */
public class ScopedContextWrapper extends ContextWrapper implements Injector {
    final @NotNull @Getter ObjectGraph    objectGraph;
    @Nullable              LayoutInflater inflater;

    public ScopedContextWrapper(@NotNull Context base, @NotNull ObjectGraph objectGraph) {
        super(base);
        this.objectGraph = objectGraph;
    }

    @NotNull @Override public Object getSystemService(@NotNull String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (inflater == null) {
                inflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return inflater;
        }
        return getBaseContext().getSystemService(name);
    }
}
