package com.projects.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Utils.MoviePosterPathBuilder;
import com.projects.android.popularmovies.Utils.MovieRequestBuilder;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent parentIntent = getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra(getString(R.string.movie_extra))){
                Movie movie = (Movie) parentIntent.getSerializableExtra(getString(R.string.movie_extra));
                fillOutMovieDetailView(movie);
                handleFavoriteToggle();
                int movieId = movie.getId();
                MovieRequestBuilder movieRequestBuilder = new MovieRequestBuilder(this);
                String reviewsUrl = movieRequestBuilder.buildReviewsRequest(movieId);
                String previewsUrl = movieRequestBuilder.buildPreviewsRequest(movieId);


            }
        }
    }

    private void handleFavoriteToggle() {
        ToggleButton favoriteToggle = findViewById(R.id.tb_favorite_movie);
        favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    showToast("Saving to database");
                } else {
                    // The toggle is disabled
                    showToast("Remove from database");
                }
            }
        });
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
