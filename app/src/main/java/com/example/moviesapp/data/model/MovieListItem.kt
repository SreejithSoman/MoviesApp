package com.example.moviesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster-image")
    val poster_image: String
) : Parcelable
