package com.example.chiandroidinternship.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shibe(
    val id: String,
    val url: String,
    var isFavourite: Boolean
) : Parcelable