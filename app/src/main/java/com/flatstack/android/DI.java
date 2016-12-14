package com.flatstack.android;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Created by Ilya Eremin on 14.03.2016.
 */

/**
 * When you need different dependencies for tests and prod
 * then create new flavors: mock and producation and move these dependencies there
 */
public class DI {

    @NonNull public static File getCacheDir(@NonNull Context context) {
        final File external = context.getExternalCacheDir();
        return external != null ? external : context.getCacheDir();
    }
}
