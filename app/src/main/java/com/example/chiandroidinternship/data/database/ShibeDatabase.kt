package com.example.chiandroidinternship.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chiandroidinternship.data.database.dao.FavouriteShibeDao
import com.example.chiandroidinternship.data.database.entity.ShibeEntity
import com.example.chiandroidinternship.data.model.Shibe

@Database(entities = [ShibeEntity::class], version = 2)
abstract class ShibeDatabase : RoomDatabase() {
    abstract fun getFavouriteShibesDao(): FavouriteShibeDao
}