package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.usecase

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.repository.PhotoRepository
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [PhotosViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 *
 * Created by bishal on 12/06/2023
 */
class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) : SingleUseCase<List<Photo>>() {

    private var albumId: Long? = null

    fun saveAlbumId(id: Long) {
        albumId = id
    }

    override fun buildUseCaseSingle(): Single<List<Photo>> {
        return repository.getPhotos(albumId)
    }
}