package com.example.moviesapp.di.builder

import com.example.moviesapp.di.MainModule
import com.example.moviesapp.di.MoviesFragmentProvider
import com.example.moviesapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainModule::class), (MoviesFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity
}