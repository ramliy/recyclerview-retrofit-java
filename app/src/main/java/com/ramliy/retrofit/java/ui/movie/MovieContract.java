package com.ramliy.retrofit.java.ui.movie;

import com.ramliy.retrofit.java.model.movie.Movie;

import java.util.List;

/**
 * Created by ramliy10 on 16/06/20.
 */
public class MovieContract {
    public interface MovieView{
        void setProgressIndicator(final boolean active);
        void showError(String message);
        void showEmpty(String message);
        void onMoviesLoaded(List<Movie> itemList);
    }

    public interface MovieAction{
        void loadMovies();
    }
}
