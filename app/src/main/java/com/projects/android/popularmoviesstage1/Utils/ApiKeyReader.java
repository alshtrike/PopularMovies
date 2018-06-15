package com.projects.android.popularmoviesstage1.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by alshtray on 6/14/18.
 */

public class ApiKeyReader {
    private final static String TAG = ApiKeyReader.class.getCanonicalName();

    public static String readApiKey(Object obj, String apiKeyFilePath) throws IOException {
        String apiKey = "";
        FileInputStream stream;
        BufferedReader reader;
        final File file = getFileFromPath(obj, apiKeyFilePath);

        if (file.exists()) {
            stream = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(stream));
            apiKey = reader.readLine();
        }
        else{
            Log.e(TAG, "Could not read api key file. Either file doesn't exist or it is not in the correct place.");
            Log.e(TAG, "read the README for instructions on generating required api keys");
        }

        return apiKey;
    }

    private static File getFileFromPath(Object obj, String fileName) {
        // method taken from stackoverflow:
        // https://stackoverflow.com/questions/29341744/android-studio-unit-testing-read-data-input-file
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }
}
