package com.flatsoft.base.utils;

import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;

public @SharedPreferences interface Preferences {
    @Default(ofString = "XXXX") public String defaultString();
}
