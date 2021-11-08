package com.example.moviesapp.di

import com.example.moviesapp.ui.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesFragmentProvider {
    @ContributesAndroidInjector(modules =[(MovieFragmentModule::class),(MoviesSourceModule::class),])
    internal abstract fun provideMainFragmentFactory(): MoviesFragment
}