package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.repository

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.mapper.toEntity
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.AppDatabase
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.remote.RetrofitService
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.repository.PhotoRepository
import io.reactivex.Single
/**
 *
 * This repository is responsible for
 * fetching data [photo] from server or db
 *
 * Created by bishal on 12/06/2023
 * */
class PhotoRepositoryImp(
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
) : PhotoRepository {

    override fun isFavorite(photoId: Long): Boolean {
        return database.photoDao.loadOneByPhotoId(photoId) != null
    }

    override fun deletePhoto(photo: Photo) {
        database.photoDao.delete(photo.toEntity())
    }

    override fun addPhoto(photo: Photo) {
        database.photoDao.insert(photo.toEntity())
    }

    override fun getPhotoDetail(photoId: Long?): Single<Photo> {
        return retrofitService.getPhotoDetail(photoId!!)
    }

    override fun getPhotos(albumId: Long?): Single<List<Photo>> {
        return retrofitService.getPhotos(albumId!!)
    }
}