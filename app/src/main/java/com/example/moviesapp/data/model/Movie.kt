package com.example.moviesapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    val name: String,
    val poster_image: String
) : Parcelable
