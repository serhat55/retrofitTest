package com.example.retrofittest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.movies_recyler_view) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface::class.java)

        val call = apiInterface.getLastMovies()
        call.enqueue(object :retrofit2.Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Error" ,t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies = response.body()?.data
                recyclerView.adapter = MoviesAdapter(movies!!, R.layout.list_item_movie ,applicationContext)
            }


        })
        
    }

    inner class MoviesAdapter(val movies: Movies ,val rowLayout: Int ,val context: Context) :RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){


        inner class MovieViewHolder(v: View) :RecyclerView.ViewHolder(v){

            internal var moviesLayout: LinearLayout
            internal var movieTitle: TextView
            internal var data: TextView
            internal var movieDescription: TextView
            internal var rating: TextView
            internal var image: ImageView


            init {
                moviesLayout = v.findViewById(R.id.movies_layout)
                movieTitle = v.findViewById(R.id.title)
                data = v.findViewById(R.id.subtitle)
                movieDescription = v.findViewById(R.id.description)
                rating = v.findViewById(R.id.rating)
                image = v.findViewById(R.id.imageView) as ImageView
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.MovieViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(rowLayout ,parent ,false)
            return MovieViewHolder(view)

        }

        override fun getItemCount(): Int {
            return movies.movies!!.size
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

            holder.movieTitle.text = movies.movies!![position].title
            holder.data.text = movies.movies[position].year
            holder.movieDescription.text = movies.movies[position].summary
            holder.rating.text = movies.movies[position].rating
            //Picasso.with(context).load(movies.movies[position].image).into(holder.image)
            Picasso.get().load(movies.movies[position].image).into(holder.image)
        }

    }

}
