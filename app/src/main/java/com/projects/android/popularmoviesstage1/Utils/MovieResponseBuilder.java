package com.projects.android.popularmoviesstage1.Utils;

import android.net.Uri;
import android.util.Log;

import com.projects.android.popularmoviesstage1.Data.Movie;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MovieResponseBuilder {

    private static final String TAG = MovieResponseBuilder.class.getSimpleName();

    public static Movie[] buildMovieResponse(String request){
       String jsonResponse = null;

        try {
            jsonResponse = getJsonResponse(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(jsonResponse!=null){
            try {
                return MovieResponseSerializer.serializeJSON(jsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String getJsonResponse(String request) throws IOException {
        URL requestUrl = buildUrl(request);

        //taken from sunshine exercise
        HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    private static URL buildUrl(String request) {
        Uri uri = Uri.parse(request);
        Log.d(TAG, "built uri is: "+uri.toString());

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
