package com.example.chiandroidinternship.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "shibe_table")
data class ShibeEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val url: String,
    var isFavourite: Boolean
) : Parcelable