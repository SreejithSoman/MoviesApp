package com.example.moviesapp.data.repository

import androidx.paging.PagingSource
import com.example.moviesapp.data.model.Movie
import javax.inject.Inject

class MovieSourceFactory @Inject constructor() {

    @Inject
    lateinit var movieFilterSource: MovieSearchSource

    @Inject
    lateinit var moviePagingSource: MoviePagingSource

    fun getSource( filterText: String): PagingSource<Int, Movie> {
        return if (filterText.isBlank() || filterText.isEmpty()) moviePagingSource
        else movieFilterSource.apply { this.filterText = filterText }
    }
}