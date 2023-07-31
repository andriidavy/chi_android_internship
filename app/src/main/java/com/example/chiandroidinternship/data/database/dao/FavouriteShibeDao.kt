package com.example.chiandroidinternship.data.database.dao

import androidx.room.*
import com.example.chiandroidinternship.data.database.entity.ShibeEntity
import com.example.chiandroidinternship.data.model.Shibe
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteShibeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shibe: ShibeEntity)

    @Query("SELECT * FROM shibe_table")
    fun getAllShibes(): Flow<List<ShibeEntity>>

    @Query("DELETE FROM shibe_table WHERE id =:id")
    suspend fun delete(id: String)
}