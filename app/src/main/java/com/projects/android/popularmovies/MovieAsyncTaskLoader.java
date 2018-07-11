package com.projects.android.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;
import android.widget.ProgressBar;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Utils.MovieResponseBuilder;

class MovieAsyncTaskLoader extends AsyncTaskLoader<Movie[]> {

    private final Bundle args;
    private Movie[] mMovieData;
    private ProgressBar mLoadingIndicator;

    public MovieAsyncTaskLoader(Bundle args, Context context, ProgressBar loadingIndicator) {
        super(context);
        this.args = args;
        mMovieData = null;
        mLoadingIndicator = loadingIndicator;
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
        String request = args.getString(getContext().getString(R.string.request_extra));
        return MovieResponseBuilder.buildMovieResponse(request);
    }
}
