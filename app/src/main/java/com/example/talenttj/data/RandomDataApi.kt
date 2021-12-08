package com.example.talenttj.data

import com.example.talenttj.data.room.entities.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomDataApi {
    @GET("/api/users/random_user")
    suspend fun getListServer(
        @Query("size") size: Int
    ): List<User>

    // кэширования без пагинации
    @GET("/api/users/random_user?size=25")
    fun getListRetrofit(): Call<List<User>>
}