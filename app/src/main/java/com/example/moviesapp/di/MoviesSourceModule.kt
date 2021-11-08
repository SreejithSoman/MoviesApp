package com.example.moviesapp.di

import com.example.moviesapp.data.repository.MoviePagingSource
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.data.repository.MovieSearchSource
import com.example.moviesapp.data.repository.MovieSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesSourceModule {
    @Provides
    internal fun provideMoviesSource(): MovieSourceFactory {
        return MovieSourceFactory()
    }

    @Provides
    @Singleton
    internal fun provideMoviesPagingSource(movieRepo: MovieRepository): MoviePagingSource {
        return MoviePagingSource(movieRepo)
    }

    @Provides
    @Singleton
    internal fun provideMoviesFilterSource(movieRepo: MovieRepository): MovieSearchSource {
        return MovieSearchSource(movieRepo)
    }
}