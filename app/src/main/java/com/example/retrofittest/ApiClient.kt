package com.example.retrofittest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ApiClient {

    var retrofit : Retrofit? = null
    val BASE_URL : String = "https://yts.am/api/v2/"

    fun getRetrofitInstance(): Retrofit {

        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return this!!.retrofit!!
    }
}
