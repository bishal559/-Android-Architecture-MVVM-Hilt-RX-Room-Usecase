package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.repository

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.remote.RetrofitService
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.repository.AlbumRepository
import io.reactivex.Single

/**
 *
 * This repository is responsible for
 * fetching data[Album] from server or db
 *
 * Created by bishal on 12/06/2023
 * */
class AlbumRepositoryImp(
    private val retrofitService: RetrofitService
) : AlbumRepository {

    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbums()
    }
}