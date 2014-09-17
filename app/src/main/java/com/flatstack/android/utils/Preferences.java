package com.flatstack.android.utils;

import org.jetbrains.annotations.NotNull;

import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;

public @SharedPreferences interface Preferences {
    @Default(ofString = "XXXX") public String defaultString();

    public void defaultString(@NotNull String s);
}
