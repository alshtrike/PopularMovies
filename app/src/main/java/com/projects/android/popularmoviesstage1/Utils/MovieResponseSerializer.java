package com.projects.android.popularmoviesstage1.Utils;

import com.projects.android.popularmoviesstage1.Data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alshtray on 6/19/18.
 */

public class MovieResponseSerializer {

    /*the response we're looking for is inside the results array*/
    private final static String RESULTS = "results";
    private final static String TITLE = "title";
    private final static String IMAGE = "poster_path";
    private final static String OVERVIEW = "overview";
    private final static String RATING = "vote_average";
    private final static String DATE = "release_date";
    private final static String BASE_IMAGE_URL ="https://image.tmdb.org/t/p/w185";

    public static Movie[] serializeJSON(String json) throws JSONException{
        Movie[] movieArray = null;

        JSONObject movieJson = new JSONObject(json);
        JSONArray movieJsonArray = movieJson.optJSONArray(RESULTS);
        int sizeOfMovieArray = movieJsonArray.length();
        movieArray = new Movie[sizeOfMovieArray];

        for(int i=0; i<sizeOfMovieArray; i++){
            JSONObject movie = movieJsonArray.optJSONObject(i);
            movieArray[i] = parseMovie(movie);
        }
        return  movieArray;
    }

    private static Movie parseMovie(JSONObject movieObj) {

        String title = movieObj.optString(TITLE);
        String image = movieObj.optString(IMAGE);
        String overview = movieObj.optString(OVERVIEW);
        String rating = String.valueOf(movieObj.optLong(RATING));
        String date = movieObj.optString(DATE);

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setImage(BASE_IMAGE_URL+image);
        movie.setOverview(overview);
        movie.setRating(rating);
        movie.setDate(date);

        return movie;
    }
}
