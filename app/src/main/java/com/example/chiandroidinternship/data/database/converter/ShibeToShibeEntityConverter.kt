package com.example.chiandroidinternship.data.database.converter

import com.example.chiandroidinternship.data.database.entity.ShibeEntity
import com.example.chiandroidinternship.data.model.Shibe
import javax.inject.Inject

class ShibeToShibeEntityConverter @Inject constructor(){
    fun convert (shibe: Shibe) : ShibeEntity{
        return ShibeEntity(id = shibe.id, url = shibe.url, isFavourite = shibe.isFavourite)
    }
}