package com.example.theaterhelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter (private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val movieNameTextView = itemView.findViewById<TextView>(R.id.movieTitleTextView)
        val descriptionTextView = itemView.findViewById<TextView>(R.id.descriptionTextView)
        val posterImageView = itemView.findViewById<ImageView>(R.id.poster)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.movie_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: MovieAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val movie: Movie = movies.get(position)
        // Set item views based on your views and data model
        val movieNameTextView = viewHolder.movieNameTextView
        movieNameTextView.text = movie.title
        val descriptionTextView = viewHolder.descriptionTextView
        descriptionTextView.text = movie.overview
        val posterImageView = viewHolder.posterImageView
        Glide.with(posterImageView).load(movie.imageUrl).centerInside().into(posterImageView)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return movies.size
    }
}