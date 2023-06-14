package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.usecase

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.repository.AlbumRepository
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 *
 * Created by bishal on 12/06/2023
 */
class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) : SingleUseCase<List<Album>>() {

    override fun buildUseCaseSingle(): Single<List<Album>> {
        return repository.getAlbums()
    }
}