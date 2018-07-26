package com.projects.android.popularmovies.Loaders;


import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.projects.android.popularmovies.Data.MovieReview;

public class MovieReviewAsyncLoader extends AsyncLoader<MovieReview[]>{

    public MovieReviewAsyncLoader(Bundle args, Context context, ProgressBar loadingIndicator){
        super(args, context, loadingIndicator);
    }

    @Override
    public MovieReview[] loadInBackground() {
        return new MovieReview[0];
    }
}
