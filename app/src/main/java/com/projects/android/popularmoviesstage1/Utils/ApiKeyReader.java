package com.projects.android.popularmoviesstage1.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by alshtray on 6/14/18.
 */

public class ApiKeyReader {
    private final static String TAG = ApiKeyReader.class.getCanonicalName();

    public static String readApiKey(String apiKeyFilePath) throws IOException {
        String apiKey ="";
        FileInputStream is;
        BufferedReader reader;
        final File file = new File(apiKeyFilePath);

        if (file.exists()) {
            is = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(is));
            apiKey = reader.readLine();
        }
        else{
            //Log.e(TAG, "Could not read api key file. Either file doesn't exist or it is not in the correct place.");
            //Log.e(TAG, "read the README for instructions on generating required api keys");
        }

        return apiKey;
    }
}
