package com.companyname.appname.dagger;

import android.app.Fragment;
import android.content.Context;
import android.view.View;

import com.companyname.appname.App;
import com.companyname.appname.MainActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dagger.ObjectGraph;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class Dagger {
    public static ObjectGraph getAppScope(@NotNull Context context) {
        @NotNull @SuppressWarnings("all") App app = (App) context.getApplicationContext();
        return app.getObjectGraph();
    }

    @Nullable public static ObjectGraph getUiScope(@NotNull Context context) {
        return ((MainActivity) context).getObjectGraph();
    }

    public static ObjectGraph getObjectGraph(Context context) {
        // TODO manage null UI scope
        return context instanceof MainActivity ?
                getUiScope(context) :
                getAppScope(context);
    }

    public static void inject(Object object) {
        @Nullable ObjectGraph objectGraph = null;
        if (object instanceof Context) {
            objectGraph = getObjectGraph((Context) object);
        } else if (object instanceof Fragment) {
            objectGraph = getObjectGraph(((Fragment) object).getActivity());
        } else if (object instanceof View) {
            objectGraph = getObjectGraph(((View) object).getContext());
        }
        if (objectGraph != null) {
            objectGraph.inject(object);
        }
    }
}