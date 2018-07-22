package com.projects.android.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;
import android.widget.ProgressBar;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Strategies.LoadStrategy;

class MovieAsyncTaskLoader extends AsyncTaskLoader<Movie[]> {

    private final Bundle args;
    private Movie[] mMovieData;
    private ProgressBar mLoadingIndicator;
    private LoadStrategy mLoadStrategy;

    public MovieAsyncTaskLoader(Bundle args, Context context, ProgressBar loadingIndicator, LoadStrategy loadStrategy) {
        super(context);
        this.args = args;
        mMovieData = null;
        mLoadingIndicator = loadingIndicator;
        mLoadStrategy = loadStrategy;
    }

    @Override
    public void deliverResult(Movie[] data) {
        mMovieData = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (args == null) {
            return;
        }

        if (mMovieData != null) {
            deliverResult(mMovieData);
        } else {
            mLoadingIndicator.setVisibility(View.VISIBLE);
            forceLoad();
        }
    }

    @Override
    public Movie[] loadInBackground() {
        return mLoadStrategy.load();
    }
}
