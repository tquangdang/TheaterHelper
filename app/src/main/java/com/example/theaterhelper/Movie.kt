package com.example.theaterhelper

import org.json.JSONArray

data class Movie(
    val title: String,
    val overview: String,
    private val imagePath: String,
) {
    val imageUrl = "https://image.tmdb.org/t/p/w342/$imagePath"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("poster_path")
                    )
                )
            }
            return movies
        }
    }
}
