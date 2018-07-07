package com.projects.android.popularmovies.Utils;

import android.content.Context;

import com.projects.android.popularmovies.R;

public class MoviePosterPathBuilder {

    public static String buildMovieGridPosterPath(Context context, String imageUrl){
        return buildMoviePosterPath(context, imageUrl, R.string.image_size_grid);
    }

    public static String buildMovieDetailPosterPath(Context context, String imageUrl){
        return buildMoviePosterPath(context, imageUrl, R.string.image_size_detail);
    }

    private static String buildMoviePosterPath(Context context, String imageUrl, int imageSizeId){
        String baseMovieUrl = context.getString(R.string.base_image_url);
        String movieSize = context.getString(imageSizeId);
        return baseMovieUrl+movieSize+imageUrl;
    }
}
