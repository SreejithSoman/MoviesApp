package com.example.moviesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviePage(
    @SerializedName("page")
    val page: MoviePageItem
) : Parcelable
