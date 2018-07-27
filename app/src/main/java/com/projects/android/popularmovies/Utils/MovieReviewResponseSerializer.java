package com.projects.android.popularmovies.Utils;

import com.projects.android.popularmovies.Data.MovieReview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieReviewResponseSerializer implements ResponseSerializer<MovieReview[]> {
    private final static String RESULTS = "results";
    private final static String AUTHOR = "author";
    private final static String REVIEW = "content";
    private final static String URL = "url";


    @Override
    public MovieReview[] serializeResponse(String json) throws JSONException {
        MovieReview[] reviews;
        JSONObject reviewJson = new JSONObject(json);
        JSONArray reviewJsonArray = reviewJson.optJSONArray(RESULTS);
        int sizeOfReviewArray = reviewJsonArray.length();
        reviews = new MovieReview[sizeOfReviewArray];

        for(int i=0; i<sizeOfReviewArray; i++){
            JSONObject review = reviewJsonArray.optJSONObject(i);
            reviews[i] = parseReview(review);
        }
        return reviews;
    }

    private MovieReview parseReview(JSONObject reviewJson) {
        String author = reviewJson.optString(AUTHOR);
        String reviewText = reviewJson.optString(REVIEW);
        String reviewUrl = reviewJson.optString(URL);

        MovieReview review = new MovieReview();
        review.setAuthor(author);
        review.setReview(reviewText);
        review.setUrl(reviewUrl);
        return review;
    }
}
