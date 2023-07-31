package com.example.chiandroidinternship.data.repository

import com.example.chiandroidinternship.data.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun getAllUsers(): Flow<List<User>>
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}