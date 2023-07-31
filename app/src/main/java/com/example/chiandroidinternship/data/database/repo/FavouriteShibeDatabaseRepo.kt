package com.example.chiandroidinternship.data.database.repo

import com.example.chiandroidinternship.data.database.converter.ShibeEntityToShibeConverter
import com.example.chiandroidinternship.data.database.converter.ShibeToShibeEntityConverter
import com.example.chiandroidinternship.data.database.dao.FavouriteShibeDao
import com.example.chiandroidinternship.data.model.Shibe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteShibeDatabaseRepo @Inject constructor(
    private val favouriteShibeDao: FavouriteShibeDao,
    private val shibeToShibeEntityConverter: ShibeToShibeEntityConverter,
    private val shibeEntityToShibeConverter: ShibeEntityToShibeConverter
) {
    suspend fun insert(shibe: Shibe) {
        favouriteShibeDao.insert(shibeToShibeEntityConverter.convert(shibe))
    }

    fun getAllShibes(): Flow<List<Shibe>> {
        return favouriteShibeDao.getAllShibes()
            .map { it.map { shibeEntityToShibeConverter.convert(it) } }
    }

    suspend fun delete(id: String) {
        favouriteShibeDao.delete(id)
    }
}