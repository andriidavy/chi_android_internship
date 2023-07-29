package com.example.chiandroidinternship.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chiandroidinternship.data.db.dao.UserDao
import com.example.chiandroidinternship.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        private var database: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, UserDatabase::class.java, name = "db").build()
                database as UserDatabase
            } else {
                database as UserDatabase
            }
        }
    }
}