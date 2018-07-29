package com.projects.android.popularmovies.Loaders;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Strategies.LoadStrategy;

public class MovieAsyncLoader extends AsyncLoader<Movie[]> {

    private final LoadStrategy mLoadStrategy;

    public MovieAsyncLoader(Bundle args, Context context, ProgressBar loadingIndicator, LoadStrategy loadStrategy) {
        super(args, context, loadingIndicator);
        mLoadStrategy = loadStrategy;
    }

    @Override
    public Movie[] loadInBackground() {
        return mLoadStrategy.load();
    }
}
