package com.example.moviesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieContentItems(
    @SerializedName("content")
    val content: ArrayList<MovieListItem>
) : Parcelable
