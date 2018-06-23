package com.projects.android.popularmoviesstage1.Data;

/**
 * Created by alshtray on 6/16/18.
 */

public class Movie {


    private String mTitle;
    private String mImage;
    private String mOverview;
    private String mRating;
    private String mDate;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        this.mRating = rating;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}
