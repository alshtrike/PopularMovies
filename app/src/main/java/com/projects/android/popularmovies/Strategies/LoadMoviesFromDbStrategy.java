package com.projects.android.popularmovies.Strategies;

import android.content.Context;
import android.database.Cursor;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Data.MovieContract;

public class LoadMoviesFromDbStrategy implements LoadStrategy {

    private final Context mContext;

    public LoadMoviesFromDbStrategy(Context context){
        mContext = context;
    }

    @Override
    public Movie[] load() {
        Cursor cursor = mContext.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                MovieContract.MovieEntry._ID);
        return getMovieArrayFromCursor(cursor);

    }

    private Movie[] getMovieArrayFromCursor(Cursor cursor) {
        Movie[] movies = new Movie[cursor.getCount()];
        cursor.moveToFirst();
        int i = 0;
        while(!cursor.isAfterLast()) {
            Movie movie = new Movie();
            movie.setId(cursor.getInt(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_ID)));
            movie.setDate(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_YEAR)));
            movie.setImage(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER)));
            movie.setOverview(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_SUMMARY)));
            movie.setRating(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_RATING)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE)));
            movies[i] = movie;
            cursor.moveToNext();
            i++;
        }
        return movies;
    }
}
