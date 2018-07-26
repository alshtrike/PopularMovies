package com.projects.android.popularmovies.Utils;

import com.projects.android.popularmovies.Data.MovieReview;

import org.json.JSONException;

public class MovieReviewResponseSerializer implements ResponseSerializer<MovieReview[]> {
    private final static String RESULTS = "results";
    private final static String AUTHOR = "author";
    private final static String REVIEW = "content";
    private final static String URL = "url";


    @Override
    public MovieReview[] serializeResponse(String json) throws JSONException {
        //TODO fill out method
        return new MovieReview[0];
    }
}
