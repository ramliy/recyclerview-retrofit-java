package com.ramliy.retrofit.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramliy.retrofit.java.BuildConfig;
import com.ramliy.retrofit.java.R;
import com.ramliy.retrofit.java.model.movie.Movie;
import com.ramliy.retrofit.java.util.LDate;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ramliy10 on 16/06/20.
 */
public class MovieAdapter extends ListBaseAdapter {

    public MovieAdapter(List<Movie> itemList) {
        mItemList = itemList;
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Movie movie = (Movie) getItem(position);
        MovieAdapterViewHolder viewHolder = (MovieAdapterViewHolder) holder;
        viewHolder.bindData(movie);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvOverview, tvRating, tvRelaseDate;
        private ImageView ivPoster;

        public MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvRelaseDate = itemView.findViewById(R.id.tv_release_date);
            ivPoster = itemView.findViewById(R.id.iv_poster);
        }

        public void bindData(Movie movie) {
            Glide.with(itemView)
                    .load(BuildConfig.IMAGE_URL + movie.getPosterPath())
                    .into(ivPoster);
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvRating.setText("Rating : " + movie.getRating() + "/10");
            String date = LDate.changeFormat(movie.getReleaseDate(), LDate.defaultDateFormat(), LDate.dateFormatddMMMMyyyy());
            tvRelaseDate.setText("Released : " + date);

        }
    }
}
