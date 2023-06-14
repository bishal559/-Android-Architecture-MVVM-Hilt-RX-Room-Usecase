package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment.album

import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album

/**
 * To make an interaction between [AlbumsAdapter] & [AlbumsFragment]
 *
 * Created by bishal on 12/06/2023
 * */
interface OnAlbumsAdapterListener {
    fun showPhotos(album: Album)
}
