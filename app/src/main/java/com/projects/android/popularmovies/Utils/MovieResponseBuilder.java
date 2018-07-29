package com.projects.android.popularmovies.Utils;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MovieResponseBuilder<T> {

    private static final String TAG = MovieResponseBuilder.class.getSimpleName();
    private final ResponseSerializer<T> mResponseSerializer;

    public MovieResponseBuilder(ResponseSerializer<T> responseSerializer){
        mResponseSerializer = responseSerializer;
    }
    
    public T buildResponse(String request){

        String jsonResponse = getJsonResponse(request);

        if(jsonResponse!=null){
            try {
                return mResponseSerializer.serializeResponse(jsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String getJsonResponse(String request)  {
        URL requestUrl = buildUrl(request);
        HttpURLConnection urlConnection = null;
        try {
            //taken from sunshine exercise
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
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
