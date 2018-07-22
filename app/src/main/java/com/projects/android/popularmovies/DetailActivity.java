package com.projects.android.popularmovies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Data.MovieContract;
import com.projects.android.popularmovies.Utils.MoviePosterPathBuilder;
import com.projects.android.popularmovies.Utils.MovieRequestBuilder;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent parentIntent = getIntent();
        if(parentIntent!=null){
            if(parentIntent.hasExtra(getString(R.string.movie_extra))){
                Movie movie = (Movie) parentIntent.getSerializableExtra(getString(R.string.movie_extra));
                fillOutMovieDetailView(movie);
                handleFavoriteToggle(movie);
                int movieId = movie.getId();
                MovieRequestBuilder movieRequestBuilder = new MovieRequestBuilder(this);
                String reviewsUrl = movieRequestBuilder.buildReviewsRequest(movieId);
                String previewsUrl = movieRequestBuilder.buildPreviewsRequest(movieId);
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
}
