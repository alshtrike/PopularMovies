package com.projects.android.popularmovies.Data;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {
    public static final String AUTHORITY = "com.projects.android.popularmovies";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MOVIES = "movies";

    public static final class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE="movie_title";
        public static final String COLUMN_MOVIE_POSTER="movie_poster";
        public static final String COLUMN_MOVIE_SUMMARY="movie_summary";
        public static final String COLUMN_MOVIE_RATING="movie_rating";
        public static final String COLUMN_MOVIE_RELEASE_YEAR="movie_release_year";
    }
}
