package com.projects.android.popularmoviesstage1.Utils;

import android.content.Context;
import android.util.Log;

import com.projects.android.popularmoviesstage1.R;

/**
 * Created by alshtray on 6/14/18.
 */

public class MovieRequestBuilder {
    private String mMovieDbApiKey;
    private Context mContext;

    public MovieRequestBuilder(String movieDbApiKey, Context context){
        mMovieDbApiKey = movieDbApiKey;
        mContext = context;
    }

    public String buildPopularMoviesRequest(){
        String popularMovieRequest = buildMovieRequest(mContext.getString(R.string.movie_db_popular_request));
        return popularMovieRequest;
    }

    public String buildMovieDetailRequest(int id){
        String popularMovieRequest = buildMovieRequest(String.valueOf(id));
        return popularMovieRequest;
    }

    private String buildMovieRequest(String requestParam) {

        String movieRequest = mContext.getString(R.string.movie_db_base_url);
        movieRequest = movieRequest + requestParam + "?" + mMovieDbApiKey;
        return movieRequest;
    }
}
