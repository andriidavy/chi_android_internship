package com.example.chiandroidinternship.data.repo

import com.example.chiandroidinternship.data.ShibeApi
import com.example.chiandroidinternship.data.model.Shibe
import java.util.UUID
import javax.inject.Inject

class ShibeRepository @Inject constructor(private val shibeApi: ShibeApi) {
    suspend fun getShibes(): List<Shibe> {
        val urls = shibeApi.getShibesUrls()
        return urls.map { Shibe(id = UUID.randomUUID().toString(), it, false) }
    }

}