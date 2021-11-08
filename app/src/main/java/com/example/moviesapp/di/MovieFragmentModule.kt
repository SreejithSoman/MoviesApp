package com.example.moviesapp.di

import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.ui.MovieAdapter
import com.example.moviesapp.ui.MoviesFragment
import com.example.moviesapp.utils.AppUtils
import com.example.moviesapp.utils.GridSpacingItemDecoration
import dagger.Module
import dagger.Provides

@Module
class MovieFragmentModule {
    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        val span = AppUtils.getSpanCount(fragment.requireContext())
        return GridLayoutManager(fragment.requireContext(), span)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(fragment: MoviesFragment): GridSpacingItemDecoration {
        val span = AppUtils.getSpanCount(fragment.requireContext())
        return GridSpacingItemDecoration(span, 30, true)
    }

    @Provides
    internal fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter()
    }
}