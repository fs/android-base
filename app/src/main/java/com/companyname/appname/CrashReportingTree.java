package com.companyname.appname;

import com.crittercism.app.Crittercism;

import timber.log.Timber;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class CrashReportingTree extends Timber.HollowTree {
    @Override public void e(Throwable t, String message, Object... args) {
        Crittercism.logHandledException(t);
    }
}
