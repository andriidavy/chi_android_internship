package com.example.chiandroidinternship.data.repository

import com.example.chiandroidinternship.data.entity.User

interface UserRepository {
    suspend fun insertUser(user: User)
    suspend fun getAllUsers(): List<User>
    suspend fun updateUser(user: User)
}