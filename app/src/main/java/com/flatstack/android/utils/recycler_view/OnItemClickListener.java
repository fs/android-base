package com.flatstack.android.utils.recycler_view;

import android.support.annotation.NonNull;

/**
 * Created by ereminilya on 14/12/16.
 */
public interface OnItemClickListener<T> {

    void onItemClick(@NonNull T item);
}
