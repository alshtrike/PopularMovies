package com.projects.android.popularmoviesstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.projects.android.popularmoviesstage1.Utils.ApiKeyReader;
import com.projects.android.popularmoviesstage1.Utils.MovieRequestBuilder;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String mMovieRequest = "";
    private MovieRequestBuilder mRequestBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apiKey = "";

        try {
            apiKey = ApiKeyReader.readApiKey(this, getString(R.string.path_to_apikey));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!apiKey.isEmpty()){
            mRequestBuilder = new MovieRequestBuilder(apiKey, this);
        }

        if(mRequestBuilder!=null){
            mMovieRequest = mRequestBuilder.buildPopularMoviesRequest();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_sort_menu, menu);
        return true;
    }
}
