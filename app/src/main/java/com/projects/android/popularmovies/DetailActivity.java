package com.projects.android.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Data.MovieContract;
import com.projects.android.popularmovies.Data.MovieReview;
import com.projects.android.popularmovies.Data.MovieTrailer;
import com.projects.android.popularmovies.Loaders.MovieReviewAsyncLoader;
import com.projects.android.popularmovies.Loaders.MovieTrailerAsyncLoader;
import com.projects.android.popularmovies.Utils.MoviePosterPathBuilder;
import com.projects.android.popularmovies.Utils.MovieRequestBuilder;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements ReviewAdapter.ReviewAdapterOnClickHandler, TrailerAdapter.TrailerAdapterOnClickHandler{
    private static final String TAG = DetailActivity.class.getCanonicalName();

    private static final int REVIEW_LOADER_ID = 1;
    private static final int TRAILER_LOADER_ID = 2;

    private ProgressBar mReviewLoadingIndicator;
    private ProgressBar mTrailerLoadingIndicator;
    private TrailerAdapter mTrailerAdapter;
    private ReviewAdapter mReviewAdapter;
    private Context mContext = this;

    private LoaderManager.LoaderCallbacks<MovieReview[]> mReviewLoader = new LoaderManager.LoaderCallbacks<MovieReview[]>() {
        @Override
        public Loader<MovieReview[]> onCreateLoader(int id, Bundle args) {
            return new MovieReviewAsyncLoader(args, mContext, mReviewLoadingIndicator);
        }

        @Override
        public void onLoadFinished(Loader<MovieReview[]> loader, MovieReview[] data) {
            mReviewLoadingIndicator.setVisibility(View.INVISIBLE);
            mReviewAdapter.setReviewData(data);

            if(data == null){
                showToast(getString(R.string.movies_fetch_error));
            }
        }

        @Override
        public void onLoaderReset(Loader<MovieReview[]> loader) {
            //not using this but required to override, leaving empty
        }
    };

    private LoaderManager.LoaderCallbacks<MovieTrailer[]> mTrailerLoader = new LoaderManager.LoaderCallbacks<MovieTrailer[]>() {
        @Override
        public Loader<MovieTrailer[]> onCreateLoader(int id, Bundle args) {
            return new MovieTrailerAsyncLoader(args, mContext, mTrailerLoadingIndicator);
        }

        @Override
        public void onLoadFinished(Loader<MovieTrailer[]> loader, MovieTrailer[] data) {
            mTrailerLoadingIndicator.setVisibility(View.INVISIBLE);
            mTrailerAdapter.setTrailerData(data);

            if(data == null){
                showToast(getString(R.string.movies_fetch_error));
            }
        }

        @Override
        public void onLoaderReset(Loader<MovieTrailer[]> loader) {
            //not using this but required to override, leaving empty
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent parentIntent = getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra(getString(R.string.movie_extra))){
                Movie movie = (Movie) parentIntent.getParcelableExtra(getString(R.string.movie_extra));
                fillOutMovieDetailView(movie);
                handleFavoriteToggle(movie);
                int movieId = movie.getId();

                MovieRequestBuilder movieRequestBuilder = new MovieRequestBuilder(this);
                mReviewAdapter = new ReviewAdapter(this);
                mTrailerAdapter = new TrailerAdapter(this);
                mReviewLoadingIndicator = findViewById(R.id.pb_reviews_loading_indicator);
                mTrailerLoadingIndicator = findViewById(R.id.pb_trailers_loading_indicator);

                RecyclerView trailerRv = findViewById(R.id.rv_trailers);
                trailerRv.setAdapter(mTrailerAdapter);
                trailerRv.setLayoutManager(new LinearLayoutManager(this));

                RecyclerView reviewRv = findViewById(R.id.rv_reviews);
                reviewRv.setAdapter(mReviewAdapter);
                reviewRv.setLayoutManager(new LinearLayoutManager(this));

                Loader<MovieReview[]> reviewLoader = getSupportLoaderManager().getLoader(REVIEW_LOADER_ID);
                Loader<MovieTrailer[]> trailerLoader = getSupportLoaderManager().getLoader(TRAILER_LOADER_ID);

                Bundle reviewBundle = new Bundle();
                Bundle trailerBundle = new Bundle();

                String reviewsUrl = movieRequestBuilder.buildReviewsRequest(movieId);
                String trailersUrl = movieRequestBuilder.buildPreviewsRequest(movieId);

                reviewBundle.putString(getString(R.string.review_request_extra), reviewsUrl);
                trailerBundle.putString(getString(R.string.trailer_request_extra), trailersUrl);

                if(reviewLoader==null){
                    getSupportLoaderManager().restartLoader(REVIEW_LOADER_ID, reviewBundle, mReviewLoader);
                }else{
                    getSupportLoaderManager().initLoader(REVIEW_LOADER_ID, reviewBundle, mReviewLoader);
                }

                if(trailerLoader==null){
                    getSupportLoaderManager().restartLoader(TRAILER_LOADER_ID, trailerBundle, mTrailerLoader);
                }else{
                    getSupportLoaderManager().initLoader(TRAILER_LOADER_ID, trailerBundle, mTrailerLoader);
                }
            }
        }
    }

    private void handleFavoriteToggle(final Movie movie) {
        ToggleButton favoriteToggle = findViewById(R.id.tb_favorite_movie);
        favoriteToggle.setChecked(isAlreadyFavorited(movie.getId()));
        
        favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addMovieToFavorites(movie);
                } else {
                    String stringId = String.valueOf(movie.getId());
                    Uri uri = MovieContract.MovieEntry.CONTENT_URI;
                    uri = uri.buildUpon().appendPath(stringId).build();
                    Log.d(TAG, uri.toString());
                    getContentResolver().delete(uri, null, null);
                    showToast("Removed movie from favorites");
                }
            }
        });
    }

    private void addMovieToFavorites(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, movie.getId());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE, movie.getTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER, movie.getImage());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_SUMMARY, movie.getOverview());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_RATING, movie.getRating());
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_YEAR, movie.getDate());

        Uri uri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);

        if(uri != null) {
            showToast("Added movie to favorites");
        }
        else{
            showToast("Failed to add movie to favorites");
        }
    }

    private boolean isAlreadyFavorited(int id) {
        String[] queryId = new String[]{String.valueOf(id)};
        Cursor cursor = getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, null,
                MovieContract.MovieEntry.COLUMN_MOVIE_ID+"=?",queryId, null);
        return cursor.getCount() >0 ? true : false;
    }

    private void showToast(String toastText){
        Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    private void fillOutMovieDetailView(Movie movie) {
        TextView title = findViewById(R.id.tv_movie_detail_title);
        TextView summary = findViewById(R.id.tv_movie_detail_summary);
        TextView year = findViewById(R.id.tv_movie_release_year);
        TextView rating = findViewById(R.id.tv_movie_rating);
        ImageView detailMoviePoster = findViewById(R.id.iv_detail_movie_poster);

        title.setText(movie.getTitle());
        summary.setText(movie.getOverview());

        String ratingScore = movie.getRating()+getString(R.string.max_review_score);
        rating.setText(ratingScore);

        String yearString = getMovieReleaseYear(movie.getDate());
        year.setText(yearString);

        String movieImageUrl = MoviePosterPathBuilder.buildMovieDetailPosterPath(this, movie.getImage());

        Picasso.with(this)
                .load(movieImageUrl)
                .placeholder(R.drawable.image_placeholder_92)
                .into(detailMoviePoster);
    }

    private String getMovieReleaseYear(String date){
        String[] datePortions =  date.split("-");
        return datePortions[0];
    }

    @Override
    public void onClick(MovieReview review) {
        Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(review.getUrl()));
        startActivity(trailerIntent);
    }

    @Override
    public void onClick(MovieTrailer trailer) {
        String videoUrl = getString(R.string.youtube_url)+trailer.getLinkToTrailer();
        Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(trailerIntent);
    }
}
