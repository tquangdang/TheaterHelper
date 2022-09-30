package com.example.theaterhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

private const val TAG = "MainActivity"
private const val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MainActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the recyclerview in activity layout
        val rvMovies = findViewById<RecyclerView>(R.id.recyclerView)
        // Initialize contacts

        // Create adapter passing in the sample user data
        val adapter = MovieAdapter(movies)
        // Attach the adapter to the recyclerview to populate items
        rvMovies.adapter = adapter
        // Set layout manager to position the items
        rvMovies.layoutManager = LinearLayoutManager(this)
        // That's all!

        val client = AsyncHttpClient()
        client.get(NOW_PLAYING_URL, object: JsonHttpResponseHandler() {
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON data $json")
                val movieJsonArray = json.jsonObject.getJSONArray("results")
                movies.addAll(Movie.fromJsonArray(movieJsonArray))
                adapter.notifyDataSetChanged()
                Log.i(TAG, "Movie list $movies")
            }

        })
    }
}