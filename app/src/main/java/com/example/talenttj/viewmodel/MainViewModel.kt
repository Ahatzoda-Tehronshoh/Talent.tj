package com.example.talenttj.viewmodel

import android.app.Application
import android.media.MediaParser
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.*
import androidx.paging.*
import com.example.talenttj.R
import com.example.talenttj.data.*
import com.example.talenttj.data.repositories.MainRepository
import com.example.talenttj.data.repositories.ServerRepository
import com.example.talenttj.data.room.entities.User
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepository(application.applicationContext)
    private val serverRepository = ServerRepository(application.applicationContext)

    private val _getLiveData = MutableLiveData<PagingData<User>>()
    val getLiveData: LiveData<PagingData<User>> = _getLiveData

    private val _searchLiveData = MutableLiveData<PagingData<User>>()
    val searchLiveData: LiveData<PagingData<User>> = _searchLiveData

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(listOf(user))
            getAllUserFromRetrofit()
        }
    }

    // кэширования без пагинации
    private val _getLiveDataCash = MutableLiveData<List<User>>()
    val getLiveDataCash: LiveData<List<User>> = _getLiveDataCash

    // кэширования без пагинации
    fun getAllUserFromRetrofit() {
        serverRepository.getList().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (!response.isSuccessful) return

                if (response.body() != null) {
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.insert(response.body()!!)
                        _getLiveDataCash.postValue(repository.getListFromRoom())
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                viewModelScope.launch(Dispatchers.IO) {
                    _getLiveDataCash.postValue(repository.getListFromRoom())
                }
                Log.d("TEST_TAG", "$t")
            }
        })
    }

    fun getAllUserLocale(): Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { repository.getAllUsers() }
    ).flow.cachedIn(viewModelScope)

    private fun clearUserTable() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearUserTable()
        }
    }

    fun searchUser(profName: String, skillId: Int, cityName: String): Flow<PagingData<User>> =
        Pager(config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {
                repository.searchUser(
                    profName,
                    skillId,
                    cityName
                )
            }).flow.cachedIn(viewModelScope)


    fun getProfessionSpinnerAdapter(): ArrayAdapter<String> {
        val list = mutableListOf<String>()
        ProfessionAndSkill.getDefaultProfessions().forEach { list.add(it.professionName) }
        return ArrayAdapter(
            getApplication(),
            R.layout.support_simple_spinner_dropdown_item,
            list
        )
    }

    fun getSkillSpinnerAdapter(): ArrayAdapter<String> {
        val list = mutableListOf<String>()
        ProfessionAndSkill.getDefaultTechSkill().forEach { list.add(it.skillName) }
        return ArrayAdapter(
            this.getApplication(),
            R.layout.support_simple_spinner_dropdown_item,
            list
        )
    }
}