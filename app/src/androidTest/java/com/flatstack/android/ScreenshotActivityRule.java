package com.flatstack.android;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ScreenshotActivityRule<T extends Activity> extends IntentsTestRule<T> {

    public ScreenshotActivityRule(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        String testClassName = description.getClassName();
        String testMethodName = description.getMethodName();
        Context context = InstrumentationRegistry.getTargetContext();
        Espresso.setFailureHandler((error, matcher) -> {
            new DefaultFailureHandler(context).handle(error, matcher);
        });
        return super.apply(base, description);
    }
}
