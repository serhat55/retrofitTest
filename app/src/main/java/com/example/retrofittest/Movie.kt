package com.example.retrofittest

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("title")
    val title: String? = null

    @SerializedName("year")
    val year: String? = null

    @SerializedName("rating")
    val rating: String? = null

    @SerializedName("summary")
    val summary: String? = null

    @SerializedName("small_cover_image")
    val image: String? = null

}
