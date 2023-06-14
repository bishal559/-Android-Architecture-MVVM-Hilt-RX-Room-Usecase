package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photo")
data class PhotoEntity(
    @PrimaryKey var id: Long,
    var title: String,
    val url: String,
    val thumbnailUrl: String?
)
