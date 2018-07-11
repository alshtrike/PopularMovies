package com.projects.android.popularmovies.Utils;

import android.content.Context;
import android.util.Log;

import com.projects.android.popularmovies.BuildConfig;
import com.projects.android.popularmovies.R;

public class MovieRequestBuilder {
    private final String mMovieDbApiKey;
    private final Context mContext;
    private final static String TAG = MovieRequestBuilder.class.getCanonicalName();

    public MovieRequestBuilder(Context context){
        mMovieDbApiKey = BuildConfig.MovieDbApiKey;
        if(mMovieDbApiKey==null){
            Log.e(TAG, "Couldn't find api key. See README for how to add it");
        }
        mContext = context;
    }

    public String buildPopularMoviesRequest(){
        return buildMovieRequest(mContext.getString(R.string.movie_db_popular_request));
    }

    public String buildTopRatedMoviesRequest(){
        return buildMovieRequest(mContext.getString(R.string.movie_db_top_rated_request));
    }

    public String buildReviewsRequest(int movieId){
        return buildMovieRequest(movieId+"/reviews");

    }

    public String buildPreviewsRequest(int movieId){
        return buildMovieRequest(movieId+"/videos");
    }

    private String buildMovieRequest(String requestParam) {
        String movieRequest = mContext.getString(R.string.movie_db_base_url);
        movieRequest = movieRequest + requestParam + "?" + mContext.getString(R.string.api_key_field) + mMovieDbApiKey;
        return movieRequest;
    }
}
