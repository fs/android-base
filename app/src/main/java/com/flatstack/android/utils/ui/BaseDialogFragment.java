package com.flatstack.android.utils.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends DialogFragment {

    @LayoutRes public abstract int getLayoutRes();

    protected static <T extends BaseDialogFragment> T show(@NonNull T dialogFragment,
                                                           @NonNull FragmentActivity activity) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        Fragment prev = activity.getSupportFragmentManager()
            .findFragmentByTag(dialogFragment.getClass().getName());
        if (prev != null) {
            ft.remove(prev);
            DialogFragment df = (DialogFragment) prev;
            df.dismissAllowingStateLoss();
        }
        ft.addToBackStack(null);
        dialogFragment.show(ft, dialogFragment.getClass().getName());
        return dialogFragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parseArguments(getArguments());
        }
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
    }

    protected void restoreState(@NonNull Bundle savedState) {
    }

    protected void parseArguments(@NonNull Bundle args) {
        throw new IllegalStateException("should be overridden");
    }

    @NonNull @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    public Context context() {
        return getActivity();
    }

    public FragmentActivity activity() {
        return getActivity();
    }
}
