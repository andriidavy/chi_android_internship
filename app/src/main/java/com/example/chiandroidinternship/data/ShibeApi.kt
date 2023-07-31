package com.example.chiandroidinternship.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ShibeApi {
    @GET("/api/shibes")
    suspend fun getShibesUrls(
        @Query("count") count: Int = 10,
        @Query("urls") urls: Boolean = true,
        @Query("httpsUrls") httpsUrls: Boolean = true
    ): List<String>
}