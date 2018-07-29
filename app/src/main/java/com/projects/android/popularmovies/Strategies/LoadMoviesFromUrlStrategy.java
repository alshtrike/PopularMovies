package com.projects.android.popularmovies.Strategies;

import android.content.Context;
import android.os.Bundle;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.R;
import com.projects.android.popularmovies.Utils.MovieResponseBuilder;
import com.projects.android.popularmovies.Utils.MovieResponseSerializer;

public class LoadMoviesFromUrlStrategy implements LoadStrategy {
    private final Bundle mArgs;
    private final Context mContext;

    public LoadMoviesFromUrlStrategy(Bundle args, Context context){
        mArgs=args;
        mContext = context;
    }

    @Override
    public Movie[] load() {
        String request = mArgs.getString(mContext.getString(R.string.movie_request_extra));
        MovieResponseBuilder<Movie[]> responseBuilder = new MovieResponseBuilder<>(new MovieResponseSerializer());
        return responseBuilder.buildResponse(request);
    }
}
