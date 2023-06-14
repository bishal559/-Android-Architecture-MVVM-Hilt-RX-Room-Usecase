package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.repository

import io.reactivex.Single
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo

/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 *
 * Created by bishal on 12/06/2023
 * */
interface PhotoRepository {
    fun getPhotos(albumId: Long?): Single<List<Photo>>
    fun getPhotoDetail(photoId: Long?): Single<Photo>
    fun deletePhoto(photo: Photo)
    fun addPhoto(photo: Photo)
    fun isFavorite(photoId: Long): Boolean
}