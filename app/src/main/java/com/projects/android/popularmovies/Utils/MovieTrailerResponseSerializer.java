package com.projects.android.popularmovies.Utils;

import com.projects.android.popularmovies.Data.MovieTrailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieTrailerResponseSerializer implements ResponseSerializer<MovieTrailer[]> {
    private final static String RESULTS = "results";
    private final static String TRAILER_NAME = "name";
    private final static String TRAILER_LINK = "key";

    @Override
    public MovieTrailer[] serializeResponse(String json) throws JSONException {
        MovieTrailer[] trailers;
        JSONObject trailerJson = new JSONObject(json);
        JSONArray trailerJsonArray = trailerJson.optJSONArray(RESULTS);
        int sizeOfReviewArray = trailerJsonArray.length();
        trailers = new MovieTrailer[sizeOfReviewArray];

        for(int i=0; i<sizeOfReviewArray; i++){
            JSONObject review = trailerJsonArray.optJSONObject(i);
            trailers[i] = parseTrailer(review);
        }
        return trailers;
    }

    private MovieTrailer parseTrailer(JSONObject trailerJson) {
        String trailerName = trailerJson.optString(TRAILER_NAME);
        String trailerLink = trailerJson.optString(TRAILER_LINK);

        MovieTrailer trailer = new MovieTrailer();
        trailer.setTrailerName(trailerName);
        trailer.setLinkToTrailer(trailerLink);
        return trailer;
    }
}
