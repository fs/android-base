package com.flatstack.android.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by Ilya Eremin on 4/25/16.
 */
public class Rxs {

    public static <T> Observable.Transformer<T, T> doInBackgroundDeliverToUI() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static void runInBackground(Action0 action) {
        Observable.fromCallable(() -> {
            action.call();
            return null;
        }).subscribeOn(Schedulers.io())
                .subscribe(success -> {
                }, ignoreError -> {
                });
    }
}
