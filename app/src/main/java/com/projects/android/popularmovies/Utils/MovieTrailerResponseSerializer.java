package com.projects.android.popularmovies.Utils;

import com.projects.android.popularmovies.Data.MovieTrailer;

import org.json.JSONException;

/**
 * Created by alshtray on 7/25/18.
 */

public class MovieTrailerResponseSerializer implements ResponseSerializer<MovieTrailer[]> {
    private final static String RESULTS = "results";
    private final static String TRAILER_NAME = "name";
    private final static String TRAILER_LINK = "key";

    @Override
    public MovieTrailer[] serializeResponse(String json) throws JSONException {
        //TODO fill out method
        return new MovieTrailer[0];
    }
}
