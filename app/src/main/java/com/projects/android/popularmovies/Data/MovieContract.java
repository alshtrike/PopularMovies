package com.projects.android.popularmovies.Data;

import android.provider.BaseColumns;

public class MovieContract {

    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE="movie_title";
    }
}
