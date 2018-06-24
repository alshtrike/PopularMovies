package com.projects.android.popularmoviesstage1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.projects.android.popularmoviesstage1.Data.Movie;
import com.projects.android.popularmoviesstage1.Utils.MovieRequestBuilder;
import com.projects.android.popularmoviesstage1.Utils.MovieResponseBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MovieRequestBuilder mRequestBuilder;
    private MovieAdapter mMovieAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apiKey = BuildConfig.MovieDbApiKey;

        if(!apiKey.isEmpty()){
            mRequestBuilder = new MovieRequestBuilder(apiKey, this);
        }

        if(mRequestBuilder!=null){
            mMovieAdapter = new MovieAdapter();
            int numOfColumns = 2;
            GridLayoutManager layoutManager = new GridLayoutManager(this, numOfColumns);
            mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mMovieAdapter);
            sortMoviesByTopRated();
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
                mMovieAdapter.setMovieData(null);
                sortMoviesByPopular();
                return true;
            case R.id.sort_by_top_rated:
                mMovieAdapter.setMovieData(null);
                sortMoviesByTopRated();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void sortMoviesByTopRated() {
        String request = mRequestBuilder.buildTopRatedMoviesRequest();
        new FetchMoviesTask().execute(request);
        showToast("Top Rated Movies");
    }

    private void sortMoviesByPopular() {
        String request = mRequestBuilder.buildPopularMoviesRequest();
        new FetchMoviesTask().execute(request);
        showToast("Popular Movies");
    }

    private void showToast(String toastText){
        Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    private class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPostExecute(Movie[] movies) {
            if(movies!=null){
                mMovieAdapter.setMovieData(movies);
            }
            else{
                Log.e(TAG,"Unable to load movies successfully");
            }
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
