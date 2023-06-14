package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.gallery

import android.widget.ImageView
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album

/**
 * To make an interaction between [GalleryActivity] & its children
 *
 * Created by bishal on 12/06/2023
 * */
interface OnGalleryCallback {
    fun navigateToAlbumPage(album: Album)
    fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long)
}
