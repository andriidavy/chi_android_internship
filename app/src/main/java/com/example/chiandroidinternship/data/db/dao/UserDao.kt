package com.example.chiandroidinternship.data.db.dao

import androidx.room.*
import com.example.chiandroidinternship.data.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>

    @Update
    suspend fun update(user: User)
}