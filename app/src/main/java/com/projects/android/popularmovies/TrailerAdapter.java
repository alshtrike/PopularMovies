package com.projects.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.android.popularmovies.Data.MovieTrailer;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerAdapterViewHolder>{

    private MovieTrailer[] mTrailerData;
    private final TrailerAdapterOnClickHandler mClickHandler;

    public TrailerAdapter(TrailerAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    @Override
    public TrailerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        int listdItemLayoutId = R.layout.movie_trailer_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(listdItemLayoutId, parent, false);

        return new TrailerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerAdapterViewHolder holder, int position) {
        MovieTrailer trailerAtGivenPosition = mTrailerData[position];
        TextView movieTrailer = holder.mTrailerName;
        movieTrailer.setText(trailerAtGivenPosition.getTrailerName());
    }

    @Override
    public int getItemCount() {
        return mTrailerData == null ? 0 : mTrailerData.length;
    }

    public void setTrailerData(MovieTrailer[] trailers){
        mTrailerData = trailers;
        notifyDataSetChanged();
    }

    protected class TrailerAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView mTrailerName;

        public TrailerAdapterViewHolder(View view) {
            super(view);
            mTrailerName = view.findViewById(R.id.tv_movie_trailer);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            MovieTrailer currentTrailer = mTrailerData[getAdapterPosition()];
            mClickHandler.onClick(currentTrailer);
        }
    }

    public interface TrailerAdapterOnClickHandler {
        void onClick(MovieTrailer trailer);
    }
}
