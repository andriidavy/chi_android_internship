package com.example.chiandroidinternship.data.repository

import android.app.Application
import com.example.chiandroidinternship.data.db.UserDatabase
import com.example.chiandroidinternship.data.db.dao.UserDao
import com.example.chiandroidinternship.data.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val application: Application) : UserRepository {

    private lateinit var userDao: UserDao

    init {
        createDatabase()
    }

    override suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }

    private fun createDatabase() {
        userDao = UserDatabase.getInstance(application).getUserDao()
    }
}