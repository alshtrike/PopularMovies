package com.projects.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.android.popularmovies.Data.MovieReview;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapterViewHolder>{

    private MovieReview[] mReviewData;
    private Context mContext;
    private final ReviewAdapterOnClickHandler mClickHandler;

    public ReviewAdapter(ReviewAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    @Override
    public ReviewAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int listItemLayoutId = R.layout.movie_review_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(listItemLayoutId, parent, false);

        return new ReviewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapterViewHolder holder, int position) {
        MovieReview reviewAtGivenPosition = mReviewData[position];
        TextView reviewerName = holder.mReviewerName;
        TextView reviewText = holder.mReviewText;

        reviewerName.setText(reviewAtGivenPosition.getAuthor());
        reviewText.setText(reviewAtGivenPosition.getReview());

    }

    @Override
    public int getItemCount() {
        return mReviewData == null ? 0 : mReviewData.length;
    }

    public void setReviewData(MovieReview[] reviews){
        mReviewData = reviews;
        notifyDataSetChanged();
    }

    protected class ReviewAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView mReviewerName;
        final TextView mReviewText;

        public ReviewAdapterViewHolder(View view) {
            super(view);
            mReviewerName = view.findViewById(R.id.tv_reviewer_name);
            mReviewText = view.findViewById(R.id.tv_review_text);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            MovieReview currentMovie = mReviewData[getAdapterPosition()];
            mClickHandler.onClick(currentMovie);
        }
    }

    public interface ReviewAdapterOnClickHandler {
        void onClick(MovieReview review);
    }
}
