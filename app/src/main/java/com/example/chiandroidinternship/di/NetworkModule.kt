package com.example.chiandroidinternship.di

import com.example.chiandroidinternship.data.ShibeApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://shibe.online")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideShibeApi(retrofit: Retrofit): ShibeApi = retrofit.create(ShibeApi::class.java)
}