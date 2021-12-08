package com.example.talenttj.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.talenttj.data.repositories.MainRepository
import com.example.talenttj.data.room.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val repository = MainRepository(application.applicationContext)

    private val _getUserWithId =  MutableLiveData<User>()
    val getUserWithId: LiveData<User> = _getUserWithId

    private var _professionToString = ""
    val professionToString: String = _professionToString

    private var _listOfSkill = emptyList<String>()
    val listOfSkill: List<String> = _listOfSkill

    fun getUserWithID(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _professionToString = repository.getProfessionByID(id)
            _listOfSkill = repository.getSkillsByIdUser(id)
            _getUserWithId.postValue(repository.getUserWithId(id))
        }
    }

}