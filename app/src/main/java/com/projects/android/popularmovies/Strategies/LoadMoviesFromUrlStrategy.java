package com.projects.android.popularmovies.Strategies;

import android.content.Context;
import android.os.Bundle;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.R;
import com.projects.android.popularmovies.Utils.MovieResponseBuilder;

public class LoadMoviesFromUrlStrategy implements LoadStrategy {
    private Bundle mArgs;
    private Context mContext;

    public LoadMoviesFromUrlStrategy(Bundle args, Context context){
        mArgs=args;
        mContext = context;
    }

    @Override
    public Movie[] load() {
        String request = mArgs.getString(mContext.getString(R.string.request_extra));
        return MovieResponseBuilder.buildMovieResponse(request);
    }
}
