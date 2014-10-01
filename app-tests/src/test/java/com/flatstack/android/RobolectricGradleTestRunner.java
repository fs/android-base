package com.flatstack.android;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;

/**
 * Created by adel on 13/08/14
 */
public class RobolectricGradleTestRunner extends RobolectricTestRunner {
    static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;

    public RobolectricGradleTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override protected AndroidManifest getAppManifest(Config config) {
        String manifestProperty = "../app/src/main/AndroidManifest.xml";
        String resProperty = "../app/src/main/res";
        return new AndroidManifest(Fs.fileFromPath(manifestProperty),
                                   Fs.fileFromPath(resProperty)) {
            @Override public int getTargetSdkVersion() { return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC; }
        };
    }
}