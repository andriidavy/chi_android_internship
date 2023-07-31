package com.example.chiandroidinternship.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.chiandroidinternship.data.entity.User
import com.example.chiandroidinternship.data.repository.UserRepository
import com.example.chiandroidinternship.data.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: UserRepository

    private val _usersListFlow = MutableStateFlow(emptyList<User>())
    val usersListFlow: StateFlow<List<User>>
        get() = _usersListFlow

    init {
        repository = UserRepositoryImpl(application)
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllUsers().distinctUntilChanged()
            withContext(Dispatchers.Main) {
                result.collect {
                    _usersListFlow.value = it
                }
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun storeUser(name: String, age: String, birthday: String) {
        val ageInt = age.toInt()
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(
                User(
                    name = name,
                    age = ageInt,
                    birthday = birthday,
                    isStudent = false
                )
            )
        }
    }

    fun sortUsersListByName() {
        _usersListFlow.update { it.sortedBy { it.name } }
    }

    fun sortUsersListByAge() {
        _usersListFlow.update { it.sortedBy { it.age } }
    }

    fun sortUsersListByStatus() {
        _usersListFlow.update { it.sortedByDescending {it.isStudent } }
    }
}