package com.example.chiandroidinternship.di

import android.app.Application
import androidx.room.Room
import com.example.chiandroidinternship.data.database.ShibeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): ShibeDatabase =
        Room.databaseBuilder(
            application, ShibeDatabase::class.java, "shibeDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideShibeDao(shibeDatabase: ShibeDatabase) = shibeDatabase.getFavouriteShibesDao()
}