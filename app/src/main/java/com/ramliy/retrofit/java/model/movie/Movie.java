package com.ramliy.retrofit.java.model.movie;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ramliy10 on 16/06/20.
 */
public class Movie {
    @SerializedName("poster_path")
    private String posterPath;
    private String id;
    private String title;
    @SerializedName("vote_average")
    private String rating;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    public String getPosterPath() {
        return posterPath;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
