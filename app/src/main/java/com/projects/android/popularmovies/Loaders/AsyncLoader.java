package com.projects.android.popularmovies.Loaders;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;
import android.widget.ProgressBar;

abstract class AsyncLoader<T>  extends AsyncTaskLoader<T> {

    private final Bundle args;
    private T mData;
    private ProgressBar mLoadingIndicator;

    public AsyncLoader(Bundle args, Context context, ProgressBar loadingIndicator){
        super(context);
        this.args = args;
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
        if (args == null) {
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
