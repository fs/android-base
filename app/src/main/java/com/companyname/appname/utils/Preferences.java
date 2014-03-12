package com.companyname.appname.utils;

import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;

/**
 * Created by adelnizamutdinov on 12/03/2014
 */
public @SharedPreferences interface Preferences {
    @Default(ofString = "XXXX") public String defaultString();
}
