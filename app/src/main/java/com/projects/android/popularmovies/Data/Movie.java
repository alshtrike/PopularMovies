package com.projects.android.popularmovies.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int mId;
    private String mTitle;
    private String mImage;
    private String mOverview;
    private String mRating;
    private String mDate;

    public Movie(){
        //default empty constructor
    }

    private Movie(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mImage = in.readString();
        mOverview = in.readString();
        mRating = in.readString();
        mDate = in.readString();
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

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

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mImage);
        parcel.writeString(mOverview);
        parcel.writeString(mRating);
        parcel.writeString(mDate);
    }
}
