package com.projects.android.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.projects.android.popularmoviesstage1.Data.Movie;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = (TextView) findViewById(R.id.tv_movie_detail_title);
        TextView summary = (TextView) findViewById(R.id.tv_movie_detail_summary);

        Intent parentIntent = getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra(getString(R.string.movie_extra))){
                Movie movie = (Movie) parentIntent.getSerializableExtra(getString(R.string.movie_extra));
                title.setText(movie.getTitle());
                summary.setText(movie.getOverview());
            }
        }
    }
}
