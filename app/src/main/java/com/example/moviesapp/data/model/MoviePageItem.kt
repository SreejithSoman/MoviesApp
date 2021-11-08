package com.example.moviesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviePageItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("total-content-items")
    val total_content_items: String,
    @SerializedName("page-num")
    val page_num: String,
    @SerializedName("page-size")
    val page_size: String,
    @SerializedName("content-items")
    val content_items: MovieContentItems
) : Parcelable
