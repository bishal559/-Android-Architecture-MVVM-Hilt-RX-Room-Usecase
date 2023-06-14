package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.mapper

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.entity.PhotoEntity
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo

fun Photo.toEntity() = PhotoEntity(
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)