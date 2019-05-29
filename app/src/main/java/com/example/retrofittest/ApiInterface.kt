package com.example.retrofittest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {


    @GET("list_movies.json?sort_by=date_added")
    fun getLastMovies() : Call<MovieResponse>

}
