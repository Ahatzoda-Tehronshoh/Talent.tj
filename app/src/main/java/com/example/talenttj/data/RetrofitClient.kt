package com.example.talenttj.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://random-data-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRandomListOfUser(): RandomDataApi = retrofit.create(RandomDataApi::class.java)
}