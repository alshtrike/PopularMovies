package com.projects.android.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.android.popularmoviesstage1.Data.Movie;
import com.projects.android.popularmoviesstage1.Utils.MoviePosterPathBuilder;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.tv_movie_detail_title);
        TextView summary = findViewById(R.id.tv_movie_detail_summary);
        TextView year = findViewById(R.id.tv_movie_release_year);
        TextView rating = findViewById(R.id.tv_movie_rating);
        ImageView detailMoviePoster = findViewById(R.id.iv_detail_movie_poster);

        Intent parentIntent = getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra(getString(R.string.movie_extra))){
                Movie movie = (Movie) parentIntent.getSerializableExtra(getString(R.string.movie_extra));

                title.setText(movie.getTitle());
                summary.setText(movie.getOverview());

                String ratingScore = movie.getRating()+getString(R.string.max_review_score);
                rating.setText(ratingScore);

                String yearString = getMovieReleaseYear(movie.getDate());
                year.setText(yearString);

                String movieUrl = MoviePosterPathBuilder.buildMovieDetailPosterPath(this, movie.getImage());

                Picasso.with(this)
                        .load(movieUrl)
                        .placeholder(R.drawable.image_placeholder_92)
                        .into(detailMoviePoster);
            }
        }
    }

    private String getMovieReleaseYear(String date){
        String[] datePortions =  date.split("-");
        return datePortions[0];
    }
}
