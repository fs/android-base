package com.flatstack.android.main_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.flatstack.android.R;
import com.flatstack.android.utils.ui.BaseDialogFragment;

import butterknife.Bind;

public class TestDialog extends BaseDialogFragment {

    private static final String KEY_TITLE   = "dialogTitle";
    private static final String KEY_MESSAGE = "dialogMessage";

    @Bind(R.id.dialog_title)   TextView uiTitle;
    @Bind(R.id.dialog_message) TextView uiMessage;

    private String title;
    private String message;

    @Override public int getLayoutRes() {
        return R.layout.dialog_test;
    }

    public static void show(@Nullable String title,
                            @Nullable String message,
                            @NonNull FragmentManager fm) {

        TestDialog dialog = new TestDialog();

        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title != null ? title : "");
        args.putString(KEY_MESSAGE, message != null ? message : "");
        dialog.setArguments(args);

        dialog.show(fm, TestDialog.class.getName());
    }

    @Override protected void parseArguments(@NonNull Bundle args) {
        title = getArguments().getString(KEY_TITLE);
        message = getArguments().getString(KEY_MESSAGE);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uiTitle.setText(title != null ? title : "");
        uiMessage.setText(message != null ? message : "");
    }
}
