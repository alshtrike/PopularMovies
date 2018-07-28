package com.projects.android.popularmovies.Loaders;


import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.projects.android.popularmovies.Data.MovieReview;
import com.projects.android.popularmovies.R;
import com.projects.android.popularmovies.Utils.MovieResponseBuilder;
import com.projects.android.popularmovies.Utils.MovieReviewResponseSerializer;

public class MovieReviewAsyncLoader extends AsyncLoader<MovieReview[]>{

    public MovieReviewAsyncLoader(Bundle args, Context context, ProgressBar loadingIndicator){
        super(args, context, loadingIndicator);
    }

    @Override
    public MovieReview[] loadInBackground() {
        String request = mArgs.getString(getContext().getString(R.string.review_request_extra));
        MovieReviewResponseSerializer responseSerializer = new MovieReviewResponseSerializer();
        MovieResponseBuilder<MovieReview[]> responseBuilder = new MovieResponseBuilder<>(responseSerializer);
        return responseBuilder.buildResponse(request);
    }
}
