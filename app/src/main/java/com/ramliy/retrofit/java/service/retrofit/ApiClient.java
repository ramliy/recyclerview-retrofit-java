package com.ramliy.retrofit.java.service.retrofit;

import com.ramliy.retrofit.java.model.genre.GenreResponse;
import com.ramliy.retrofit.java.model.movie.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ramliy10 on 08/05/20.
 */
public interface ApiClient {
    @GET("genre/movie/list")
    Call<GenreResponse> getListGenres(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse> getListMovies(@Query("api_key") String apiKey);
}
