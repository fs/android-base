package com.flatstack.android.utils.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ereminilya on 14/12/16.
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);
    }

    protected abstract void bind(T item);
}
