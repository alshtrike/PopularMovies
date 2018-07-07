package com.projects.android.popularmovies.Utils;

import android.content.Context;

import com.projects.android.popularmovies.R;

public class MovieRequestBuilder {
    private final String mMovieDbApiKey;
    private final Context mContext;

    public MovieRequestBuilder(String movieDbApiKey, Context context){
        mMovieDbApiKey = movieDbApiKey;
        mContext = context;
    }

    public String buildPopularMoviesRequest(){
        return buildMovieRequest(mContext.getString(R.string.movie_db_popular_request));
    }

    public String buildTopRatedMoviesRequest(){
        return buildMovieRequest(mContext.getString(R.string.movie_db_top_rated_request));
    }

    private String buildMovieRequest(String requestParam) {
        String movieRequest = mContext.getString(R.string.movie_db_base_url);
        movieRequest = movieRequest + requestParam + "?" + mContext.getString(R.string.api_key_field) + mMovieDbApiKey;
        return movieRequest;
    }
}
