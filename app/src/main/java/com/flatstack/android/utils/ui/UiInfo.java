package com.flatstack.android.utils.ui;

import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Created by almaziskhakov on 20/12/2016.
 */

public class UiInfo {

    @LayoutRes private final int     layoutRes;
    @StringRes private       int     titleRes;
    @MenuRes private         int     menuRes;
    private                  String  titleStr;
    private                  boolean hasBackButton;

    public UiInfo(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getMenuRes() {
        return menuRes;
    }

    public UiInfo setMenuRes(int menuRes) {
        this.menuRes = menuRes;
        return this;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public UiInfo setTitleRes(int titleRes) {
        this.titleRes = titleRes;
        return this;
    }

    public UiInfo setTitle(@NonNull String title) {
        this.titleStr = title;
        return this;
    }

    public String getTitle() {
        return titleStr;
    }

    public boolean isHasBackButton() {
        return hasBackButton;
    }

    public UiInfo enableBackButton() {
        this.hasBackButton = true;
        return this;
    }
}
