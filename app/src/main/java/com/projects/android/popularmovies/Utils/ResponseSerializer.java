package com.projects.android.popularmovies.Utils;

import org.json.JSONException;

public interface ResponseSerializer<T> {
    T serializeResponse(String json) throws JSONException;
}
