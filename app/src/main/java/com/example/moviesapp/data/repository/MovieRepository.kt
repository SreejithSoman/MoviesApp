package com.example.moviesapp.data.repository

import android.content.Context
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MoviePage
import com.example.moviesapp.data.model.MoviePageItem
import com.example.moviesapp.data.model.MovieResponse
import com.example.moviesapp.ui.MovieListener
import com.example.moviesapp.utils.AppUtils
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val mContext: Context) : MovieListener {

    private var moviesResponse: MovieResponse? =null

    override fun getComedyMovies(page: Int): MovieResponse {
        val data = AppUtils.getPageData(mContext, page)
        val gson = Gson()
        val pageData = gson.fromJson(data, MoviePage::class.java)
        val movieList: ArrayList<Movie> = ArrayList()
        var id: Int = page
        if(page == 2) { id = 21 }
        else if(page == 3) { id = 51 }
        pageData.page.content_items.content.forEach {
            val movie = Movie(id, it.name, it.poster_image)
            id++
            movieList.add(movie)
        }
        val response = MovieResponse(pageData.page.page_num.toInt(), pageData.page.total_content_items.toInt(), 3, movieList)
        if (moviesResponse == null) moviesResponse = response
        else
            moviesResponse?.results.also {
                it?.addAll(response.results)
            }?.let {
                moviesResponse = response.copy()
                moviesResponse!!.results = it
            }
        return response
    }

    override fun getFilteredComedyMovies(filterText: String): MovieResponse? {
        val result = moviesResponse?.results?.filter { movie -> movie.name?.contains(filterText, true) == true }?.toList()
        result?.let {
            val response = moviesResponse?.copy()
            response?.results = ArrayList(it)
            return response
        }
        return null
    }

}