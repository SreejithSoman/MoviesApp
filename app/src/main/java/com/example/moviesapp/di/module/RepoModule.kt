package com.example.moviesapp.di.module

import android.content.Context
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.ui.MovieListener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    internal fun provideMovieRepository(mContext: Context): MovieListener {
        return MovieRepository(mContext)
    }
}