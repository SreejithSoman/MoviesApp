package com.example.moviesapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.ui.MovieListener
import javax.inject.Inject

class MovieSearchSource @Inject constructor(private val repo: MovieListener) : PagingSource<Int, Movie>() {

    var filterText:String? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return if (filterText == null) LoadResult.Error(Exception(""))
        else try {
            val movieListResponse = repo.getFilteredComedyMovies(filterText!!)!!
            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}