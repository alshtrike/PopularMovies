package com.projects.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.projects.android.popularmovies.Data.Movie;
import com.projects.android.popularmovies.Utils.MoviePosterPathBuilder;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    private Movie[] mMovieData;
    private Context mContext;
    private final MovieAdapterOnClickHandler mClickHandler;

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

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
        String movieUrl = MoviePosterPathBuilder.buildMovieGridPosterPath(mContext, movieAtGivenPosition.getImage());

        Picasso.with(mContext)
                .load(movieUrl)
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

    protected class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final ImageView mMoviePoster;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMoviePoster = view.findViewById(R.id.iv_movie_poster);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Movie currentMovie = mMovieData[getAdapterPosition()];
            mClickHandler.onClick(currentMovie);
        }
    }

    public interface MovieAdapterOnClickHandler{
        void onClick(Movie movie);
    }
}
