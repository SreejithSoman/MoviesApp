package com.example.moviesapp.ui

import com.example.moviesapp.data.model.MovieResponse

interface MovieListener {
    fun getComedyMovies(page: Int): MovieResponse
    fun getFilteredComedyMovies(filterText: String): MovieResponse?
}