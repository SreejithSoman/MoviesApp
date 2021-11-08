package com.example.moviesapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.repository.MovieSourceFactory
import com.example.moviesapp.viewmodel.MoviesViewModel
import com.example.moviesapp.viewmodel.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    internal fun provideMoviesViewModel(sourceFactory: MovieSourceFactory): MoviesViewModel {
        return MoviesViewModel(sourceFactory)
    }

    @Provides
    internal fun provideMoviesViewModelFactory(moviesViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesViewModel)
    }
}