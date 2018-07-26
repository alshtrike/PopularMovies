package com.projects.android.popularmovies.Loaders;


import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.projects.android.popularmovies.Data.MovieTrailer;
import com.projects.android.popularmovies.R;
import com.projects.android.popularmovies.Utils.MovieResponseBuilder;
import com.projects.android.popularmovies.Utils.MovieTrailerResponseSerializer;

public class MovieTrailerAsyncLoader extends AsyncLoader<MovieTrailer[]> {

    public MovieTrailerAsyncLoader(Bundle args, Context context, ProgressBar loadingIndicator){
        super(args, context, loadingIndicator);
    }

    @Override
    public MovieTrailer[] loadInBackground() {
        String request = mArgs.getString(getContext().getString(R.string.trailer_request_extra));
        MovieTrailerResponseSerializer responseSerializer = new MovieTrailerResponseSerializer();
        MovieResponseBuilder<MovieTrailer[]> responseBuilder = new MovieResponseBuilder<>(responseSerializer);
        return responseBuilder.buildResponse(request);
    }
}
