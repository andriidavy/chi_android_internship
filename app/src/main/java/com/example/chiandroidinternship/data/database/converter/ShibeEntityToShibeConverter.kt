package com.example.chiandroidinternship.data.database.converter

import com.example.chiandroidinternship.data.database.entity.ShibeEntity
import com.example.chiandroidinternship.data.model.Shibe
import javax.inject.Inject

class ShibeEntityToShibeConverter @Inject constructor(){
    fun convert (shibeEntity: ShibeEntity) : Shibe{
        return Shibe(id= shibeEntity.id, url = shibeEntity.url, isFavourite = shibeEntity.isFavourite)
    }
}