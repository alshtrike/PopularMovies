package com.projects.android.popularmovies.Loaders;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;
import android.widget.ProgressBar;

abstract class AsyncLoader<T>  extends AsyncTaskLoader<T> {

    final Bundle mArgs;
    private T mData;
    private ProgressBar mLoadingIndicator;

    AsyncLoader(Bundle args, Context context, ProgressBar loadingIndicator){
        super(context);
        this.mArgs = args;
        mData = null;
        mLoadingIndicator = loadingIndicator;
    }

    @Override
    public void deliverResult(T data) {
        mData = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (mArgs == null) {
            return;
        }

        if (mData != null) {
            deliverResult(mData);
        } else {
            mLoadingIndicator.setVisibility(View.VISIBLE);
            forceLoad();
        }
    }
}
