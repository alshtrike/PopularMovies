package com.projects.android.popularmoviesstage1.Utils;


import android.content.Context;

import com.projects.android.popularmoviesstage1.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by alshtray on 6/14/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieRequestBuilderTest {

    @Mock
    Context mMockContext;

    private String mValidApiKey = "somes3cr3tzsh0ldb3secr3t";
    private int mTestMovieId = 420;
    private String mExpectedPopularMovieRequest = "https://api.themoviedb.org/3/movie/popular?api_key="+mValidApiKey;
    private String mExpectedTopRatedMovieRequest = "https://api.themoviedb.org/3/movie/top_rated?api_key="+mValidApiKey;
    private String mExpectedMovieDetailRequest = "https://api.themoviedb.org/3/movie/"+mTestMovieId+"?api_key="+mValidApiKey;
    private MovieRequestBuilder mRequestBuilder;
    private String mBuiltRequest;

    @Before
    public void setUp(){
        when(mMockContext.getString(R.string.movie_db_popular_request)).thenReturn("popular");
        when(mMockContext.getString(R.string.movie_db_top_rated_request)).thenReturn("top_rated");
        when(mMockContext.getString(R.string.api_key_field)).thenReturn("api_key=");
        when(mMockContext.getString(R.string.movie_db_base_url)).thenReturn("https://api.themoviedb.org/3/movie/");
    }

    @Test
    public void givenValidApiKeyWhenBuildPopularMoviesRequestIsCalledThenExpectedRequestShouldBeBuilt(){
        givenApiKey(mValidApiKey);
        whenBuildPopularMoviesRequestIsCalled();
        thenExpectedRequestShouldBeBuilt(mExpectedPopularMovieRequest);
    }

    @Test
    public void givenValidApiKeyWhenBuildMovieDetailRequestIsCalledThenExpectedRequestShouldBeBuilt(){
        givenApiKey(mValidApiKey);
        whenBuildMovieDetailRequestIsCalled();
        thenExpectedRequestShouldBeBuilt(mExpectedMovieDetailRequest);
    }

    @Test
    public void givenValidApiKeyWhenBuildTopRatedMoviesRequestIsCalledThenExpectedRequestShouldBeBuilt(){
        givenApiKey(mValidApiKey);
        whenBuildTopRatedMovieRequestIsCalled();
        thenExpectedRequestShouldBeBuilt(mExpectedTopRatedMovieRequest);
    }

    private void whenBuildTopRatedMovieRequestIsCalled() {
        mBuiltRequest = mRequestBuilder.buildTopRatedMoviesRequest();
    }

    private void whenBuildMovieDetailRequestIsCalled() {
        mBuiltRequest = mRequestBuilder.buildMovieDetailRequest(mTestMovieId);
    }

    private void thenExpectedRequestShouldBeBuilt(String expectedRequest) {
        assertEquals(expectedRequest, mBuiltRequest);
    }

    private void whenBuildPopularMoviesRequestIsCalled() {
        mBuiltRequest = mRequestBuilder.buildPopularMoviesRequest();
    }

    private void givenApiKey(String apiKey) {
        mRequestBuilder = new MovieRequestBuilder(apiKey, mMockContext);
    }
}
