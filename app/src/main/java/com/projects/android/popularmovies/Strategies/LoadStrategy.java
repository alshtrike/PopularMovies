package com.projects.android.popularmovies.Strategies;

import android.content.Context;
import android.os.Bundle;

import com.projects.android.popularmovies.Data.Movie;

public interface LoadStrategy {
     Movie[] load();
}
