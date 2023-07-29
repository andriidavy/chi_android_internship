package com.example.chiandroidinternship.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chiandroidinternship.data.entity.User
import com.example.chiandroidinternship.data.repository.UserRepository
import com.example.chiandroidinternship.data.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: UserRepository
    private val _usersListLiveData = MutableLiveData<List<User>>()
    val usersListLiveData: LiveData<List<User>>
        get() = _usersListLiveData

    init {
        repository = UserRepositoryImpl(application)
    }

    fun getAllUsers(): LiveData<List<User>> {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.getAllUsers()
            if (result.isEmpty()) {
                launch{
                    initUsersList()
                }.join()
                result = repository.getAllUsers()
            }
            withContext(Dispatchers.Main) {
                _usersListLiveData.value = result
            }
        }
        return usersListLiveData
    }

    private suspend fun initUsersList() {
        repository.insertUser(User(name = "user1", age = 18))
        repository.insertUser(User(name = "user2", age = 33))
        repository.insertUser(User(name = "user3", age = 28))
        repository.insertUser(User(name = "user4", age = 54))
        repository.insertUser(User(name = "user5", age = 22))
        repository.insertUser(User(name = "user6", age = 22))
        repository.insertUser(User(name = "user7", age = 27))
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
}