package com.example.moviesapp.di.component

import android.app.Application
import com.example.moviesapp.MoviesApp
import com.example.moviesapp.di.builder.ActivityBuilder
import com.example.moviesapp.di.module.AppModule
import com.example.moviesapp.di.module.RepoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (RepoModule::class), (ActivityBuilder::class)])
interface AppComponent: AndroidInjector<DaggerApplication> {

    fun inject(app: MoviesApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}