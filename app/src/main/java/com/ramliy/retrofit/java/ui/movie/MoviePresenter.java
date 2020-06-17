package com.ramliy.retrofit.java.ui.movie;

import com.ramliy.retrofit.java.BasePresenter;
import com.ramliy.retrofit.java.model.movie.Movie;
import com.ramliy.retrofit.java.model.movie.MovieRepository;
import com.ramliy.retrofit.java.util.network.RemoteCallback;

import java.util.List;

import retrofit2.Call;

/**
 * Created by ramliy10 on 16/06/20.
 */
public class MoviePresenter extends BasePresenter implements MovieContract.MovieAction {
    private MovieContract.MovieView view;

    public MoviePresenter(MovieContract.MovieView view) {
        this.view = view;
    }

    @Override
    public void loadMovies() {
        view.setProgressIndicator(true);
        Call call = MovieRepository.movies(new RemoteCallback.Load<List<Movie>>() {
            @Override
            public void onDataLoaded(List<Movie> data) {
                view.setProgressIndicator(false);
                view.onMoviesLoaded(data);
            }

            @Override
            public void onFailed(String message) {
                view.setProgressIndicator(false);
                view.showError(message);
            }

            @Override
            public void onDataEmpty(String message) {
                view.showEmpty(message);
            }
        });

        addAsCancellableCall(call);

    }
}
