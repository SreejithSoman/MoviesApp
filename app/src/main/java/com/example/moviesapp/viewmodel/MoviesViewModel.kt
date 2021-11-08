package com.example.moviesapp.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.MovieSourceFactory
import com.example.moviesapp.ui.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MoviesViewModel@Inject constructor(private val sourceFactory: MovieSourceFactory) : BaseViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
    }

    private val searchTextLiveData: MutableLiveData<String> = MutableLiveData("")
    var movies: LiveData<PagingData<Movie>> = MediatorLiveData()

    init {
        movies =  Transformations.switchMap(searchTextLiveData) { input: String ->
            return@switchMap getMoviesStream(input)
        }
    }

    private fun getMoviesStream(input: String): LiveData<PagingData<Movie>> {
        val result = viewModelScope.async { Pager(PagingConfig(PAGE_SIZE)) { sourceFactory.getSource(input) } }
        return runBlocking { result.await().liveData.cachedIn(viewModelScope)}
    }

    fun getSearchLiveData(): MutableLiveData<String> {
        return searchTextLiveData
    }

}