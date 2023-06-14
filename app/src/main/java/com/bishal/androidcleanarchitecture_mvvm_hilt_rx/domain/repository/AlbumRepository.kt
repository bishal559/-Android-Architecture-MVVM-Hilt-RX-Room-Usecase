package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.repository

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 *
 * Created by bishal on 12/06/2023
 * */
interface AlbumRepository {
    fun getAlbums(): Single<List<Album>>
}