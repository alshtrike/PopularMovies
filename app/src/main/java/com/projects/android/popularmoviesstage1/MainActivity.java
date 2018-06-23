package com.projects.android.popularmoviesstage1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.projects.android.popularmoviesstage1.Data.Movie;
import com.projects.android.popularmoviesstage1.Utils.MovieRequestBuilder;
import com.projects.android.popularmoviesstage1.Utils.MovieResponseBuilder;

public class MainActivity extends AppCompatActivity {

    private String mMovieRequest = "";
    private MovieRequestBuilder mRequestBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apiKey = BuildConfig.MovieDbApiKey;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.sort_by_popular:
                sortMoviesByPopular();
                return true;
            case R.id.sort_by_top_rated:
                sortMoviesByTopRated();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void sortMoviesByTopRated() {
        //TODO make this do real stuff, showing toast for now
        mMovieRequest = mRequestBuilder.buildTopRatedMoviesRequest();
        showToast("Top Rated Movies");
    }

    private void sortMoviesByPopular() {
        //TODO make this do real stuff, showing toast for now
        mMovieRequest = mRequestBuilder.buildPopularMoviesRequest();
        showToast("Popular Movies");
    }

    private void showToast(String toastText){
        Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    private class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPostExecute(Movie[] strings) {
            super.onPostExecute(strings);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Movie[] doInBackground(String... strings) {
            if (strings.length == 0) {
                return null;
            }

            String request = strings[0];
            return MovieResponseBuilder.buildMovieResponse(request);
        }
    }
}
