package com.ramliy.retrofit.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramliy.retrofit.java.R;
import com.ramliy.retrofit.java.model.genre.Genre;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ramliy10 on 08/05/20.
 */
public class HomeAdapter extends ListBaseAdapter {

    public HomeAdapter(List<Genre> itemList){
        mItemList = itemList;
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @NonNull
    @Override
    public HomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home,parent,false);
        return new HomeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Genre genre = (Genre) getItem(position);
        HomeAdapterViewHolder viewHolder = (HomeAdapterViewHolder) holder;
        viewHolder.bindData(genre);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private class HomeAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvOverview;

        public HomeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
        }

        public void bindData(Genre genre) {
            tvTitle.setText(genre.getName());
            tvOverview.setText(genre.getId());
        }
    }
}
