package com.projects.android.popularmovies;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Utils.MovieRequestBuilder;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler, LoaderManager.LoaderCallbacks<Movie[]> {

    private static final int MOVIES_LOADER_ID = 0;

    private MovieRequestBuilder mRequestBuilder;
    private MovieAdapter mMovieAdapter;
    private ProgressBar mLoadingIndicator;
    private String mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mRequestBuilder = new MovieRequestBuilder(this);

        if(mRequestBuilder!=null){
            mMovieAdapter = new MovieAdapter(this);
            int numOfColumns = 2;
            GridLayoutManager layoutManager = new GridLayoutManager(this, numOfColumns);
            RecyclerView mRecyclerView = findViewById(R.id.rv_movies);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mMovieAdapter);

            String requestExtra = getString(R.string.request_extra);
            if(savedInstanceState!=null){
                if(savedInstanceState.containsKey(requestExtra)){
                    mRequest = savedInstanceState.getString(requestExtra);
                    sendMoviesRequest();
                }
            }
            else{
                sortMoviesByPopular();
            }
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

    @Override
    public void onClick(Movie movie) {
        Intent startMovieDetail = new Intent(this, DetailActivity.class);
        startMovieDetail.putExtra(getString(R.string.movie_extra), movie);
        startActivity(startMovieDetail);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.request_extra), mRequest);
    }

    @Override
    public Loader<Movie[]> onCreateLoader(int id, final Bundle args) {
        return new MovieAsyncTaskLoader(args,this, mLoadingIndicator);
    }

    @Override
    public void onLoadFinished(Loader<Movie[]> loader, Movie[] data) {

        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mMovieAdapter.setMovieData(data);

        if(data == null){
            showToast(getString(R.string.movies_fetch_error));
        }
    }

    @Override
    public void onLoaderReset(Loader<Movie[]> loader) {
        //not using this but required to override, leaving empty
    }

    private void sortMoviesByTopRated() {
        mRequest = mRequestBuilder.buildTopRatedMoviesRequest();
        sendMoviesRequest();

    }

    private void sortMoviesByPopular() {
        mRequest = mRequestBuilder.buildPopularMoviesRequest();
        sendMoviesRequest();
    }

    private void sendMoviesRequest() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            LoaderManager.LoaderCallbacks<Movie[]> callback = MainActivity.this;
            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.request_extra), mRequest);

            Loader<Movie[]> movieLoader = getSupportLoaderManager().getLoader(MOVIES_LOADER_ID);

            if(movieLoader != null){
                getSupportLoaderManager().restartLoader(MOVIES_LOADER_ID, bundle, callback);
            }
            else{
                getSupportLoaderManager().initLoader(MOVIES_LOADER_ID, bundle, callback);
            }
        }
        else{
            showToast(getString(R.string.movies_fetch_error));
        }
    }

    private void showToast(String toastText){
        Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

}
