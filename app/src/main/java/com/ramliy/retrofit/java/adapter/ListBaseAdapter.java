package com.ramliy.retrofit.java.adapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ramliy10 on 08/05/20.
 */
public abstract class ListBaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> mItemList;

    public abstract Object getItem(int position);
}