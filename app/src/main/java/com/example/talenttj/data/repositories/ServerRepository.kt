package com.example.talenttj.data.repositories

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.talenttj.data.RetrofitClient
import com.example.talenttj.data.paging.UserPagingSource
import com.example.talenttj.data.room.entities.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class ServerRepository(private val context: Context) {
    // кэширования без пагинации
    fun getList(): Call<List<User>> = RetrofitClient.getRandomListOfUser().getListRetrofit()

    // paging with cashing
    /*fun getListOfUsers(): Flow<PagingData<User>> {
        return Pager(config = PagingConfig(
            pageSize = 25,
            enablePlaceholders = false
        ),
            pagingSourceFactory = {
                UserPagingSource(
                    RetrofitClient.getRandomListOfUser(),
                    MainRepository(context)
                )
            }).flow
    }*/

}