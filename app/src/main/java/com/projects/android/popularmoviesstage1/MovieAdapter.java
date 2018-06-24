package com.projects.android.popularmoviesstage1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.projects.android.popularmoviesstage1.Data.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by alshtray on 6/23/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    private Movie[] mMovieData;
    private Context mContext;

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int gridItemLayoutId = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(gridItemLayoutId, parent, false);

        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Movie movieAtGivenPosition = mMovieData[position];
        ImageView moviePoster = holder.mMoviePoster;
        Picasso.with(mContext)
                .load(movieAtGivenPosition.getImage())
                .placeholder(R.drawable.image_placeholder_185)
                .into(moviePoster);
    }

    @Override
    public int getItemCount() {
        return mMovieData == null ? 0 : mMovieData.length;
    }

    public void setMovieData(Movie[] movies){
        mMovieData = movies;
        notifyDataSetChanged();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView mMoviePoster;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMoviePoster = (ImageView) view.findViewById(R.id.movie_poster_iv);

        }
    }
}
