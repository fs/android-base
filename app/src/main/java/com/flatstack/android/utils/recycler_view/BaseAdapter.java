package com.flatstack.android.utils.recycler_view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import rx.functions.Func1;

/**
 * Created by ereminilya on 14/12/16.
 */
public class BaseAdapter<T, VH extends BaseHolder<T>> extends RecyclerView.Adapter<VH> {

    @NonNull private final Func1<ViewGroup, VH> func0;
    @NonNull private final List<T>              items;

    @Nullable private OnItemClickListener<T> onItemClickListener;

    public BaseAdapter(@NonNull List<T> items, @NonNull Func1<ViewGroup, VH> func0) {
        this(items, func0, null);
    }

    public BaseAdapter(@NonNull List<T> items, @NonNull Func1<ViewGroup, VH> func0,
                       @Nullable OnItemClickListener<T> onItemClickListener) {
        this.items = items;
        this.func0 = func0;
        this.onItemClickListener = onItemClickListener;
    }

    @Override public VH onCreateViewHolder(ViewGroup viewGroup, int holderType) {
        VH holder = func0.call(viewGroup);
        holder.itemView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onItemClick(getData().get(position));
            }
        });
        return holder;
    }

    @Override public void onBindViewHolder(VH vh, int position) {
        vh.bind(items.get(position));
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public List<T> getData() {
        return items;
    }

    public void add(T item) {
        this.items.add(item);
        notifyItemInserted(items.size() - 1);
    }
}