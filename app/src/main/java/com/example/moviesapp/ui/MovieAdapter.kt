package com.example.moviesapp.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import android.text.SpannableString

import android.text.Spannable
import com.example.moviesapp.utils.AppUtils


class MovieAdapter: PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieModelComparator) {

    private var searchText = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MovieViewHolder
        val movie = getItem(position)
        if(movie?.poster_image != null) {
            if (movie.poster_image.contains("poster1.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster1)
            } else if (movie.poster_image.contains("poster2.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster2)
            } else if (movie.poster_image.contains("poster3.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster3)
            } else if (movie.poster_image.contains("poster4.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster4)
            } else if (movie.poster_image.contains("poster5.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster5)
            } else if (movie.poster_image.contains("poster6.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster6)
            } else if (movie.poster_image.contains("poster7.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster7)
            } else if (movie.poster_image.contains("poster8.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster8)
            } else if (movie.poster_image.contains("poster9.jpg", ignoreCase = true)) {
                viewHolder.ivPoster.setImageResource(R.drawable.poster9)
            } else {
                viewHolder.ivPoster.setImageResource(R.drawable.placeholder_for_missing_posters)
            }
        } else {
            viewHolder.ivPoster.setImageResource(R.drawable.placeholder_for_missing_posters)
        }
        viewHolder.txtTitle.text = movie?.name ?: ""
        if(searchText.isNotBlank()) {
            val spannable: Spannable = SpannableString(movie?.name)
            AppUtils.setColorForPath(spannable, arrayOf(searchText), Color.YELLOW)
            viewHolder.txtTitle.text = spannable
        }
    }

    fun searchEnabled(text: String?) {
        searchText = if(!text.isNullOrBlank()) {
            text
        } else {
            ""
        }
        notifyDataSetChanged()
    }


    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPoster: ImageView = itemView.findViewById(R.id.movieImg) as ImageView
        var txtTitle: TextView = itemView.findViewById(R.id.movieText) as TextView
    }


    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }


}
