package com.ramliy.retrofit.java.model.movie;

import com.ramliy.retrofit.java.BuildConfig;
import com.ramliy.retrofit.java.service.retrofit.ApiService;
import com.ramliy.retrofit.java.util.network.RemoteCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ramliy10 on 16/06/20.
 */
public class MovieRepository {

    public static Call movies(final RemoteCallback.Load<List<Movie>> callback){
        Call<MovieResponse> call = ApiService.getInstance().getListMovies(BuildConfig.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onDataLoaded(response.body().getResults());
                        return;
                    } callback.onDataEmpty(response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });

        return call;

    }
}
