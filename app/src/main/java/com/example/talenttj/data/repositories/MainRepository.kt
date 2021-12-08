package com.example.talenttj.data.repositories

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.talenttj.data.room.database.UserDatabase
import com.example.talenttj.data.room.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.coroutineContext

class MainRepository(
    private val context: Context
) {
    private val dao = UserDatabase.getInstance(context).userDao()

    fun getAllUsers(): PagingSource<Int, User> {
        return dao.getAll()
    }

    fun getListFromRoom(): List<User> {
        return dao.getAllFromRoom()
    }

    fun searchUser(
        profName: String,
        skillId: Int,
        cityName: String
    ): PagingSource<Int, User> {
        return dao.searchUser(profName, skillId, cityName)
    }


    suspend fun getUserWithId(id: Int): User = dao.getUserWithID(id)

    suspend fun getProfessionByID(id: Int): String = dao.getProfessionByID(id)

    suspend fun getSkillsByIdUser(id: Int): List<String> = dao.getSkillsByIdUser(id)

    suspend fun insert(list: List<User>) = dao.insert(list)

    suspend fun clearUserTable() = dao.clearUserTable()
}