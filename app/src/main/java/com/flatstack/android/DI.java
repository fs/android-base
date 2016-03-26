package com.flatstack.android;

import android.os.Environment;

import java.io.File;

/**
 * Created by Ilya Eremin on 14.03.2016.
 */

/**
 * When you need different dependencies for tests and prod
 * then create new flavors: mock and producation and move these dependencies there
 */
public class DI {

    public static File getCacheDir() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
            ? App.appContext.getExternalCacheDir()
            : App.appContext.getCacheDir();
    }

// just example how implement proper singleton
//    private static volatile Glide glideInstance = null;
//    public static Glide getImageLoader() {
    //    Glide localInstance = glideInstance;
    //    if (localInstance == null) {
    //        synchronized (Glide.class) {
    //            localInstance = glideInstance;
    //            if (localInstance == null) {
    //                glideInstance = localInstance = Glide.get(App.appContext);
    //            }
    //        }
    //    }
    //    return localInstance;
//}

}
