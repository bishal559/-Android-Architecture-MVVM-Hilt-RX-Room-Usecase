package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.remote

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.util.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET(Constants.GET_ALBUMS)
    fun getAlbums(): Single<List<Album>>

    @GET(Constants.GET_ALBUMS_PHOTOS)
    fun getPhotos(@Path("id") id: Long): Single<List<Photo>>

    @GET(Constants.PHOTO_DETAIL)
    fun getPhotoDetail(@Path("id") id: Long): Single<Photo>
}