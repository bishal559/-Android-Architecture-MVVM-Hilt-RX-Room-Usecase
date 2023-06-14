package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.photo

import android.widget.ImageView

/**
 * To make an interaction between [PhotosAdapter] & [PhotosFragment]
 *
 * Created by bishal on 12/06/2023
 * */
interface OnPhotosAdapterListener {
    fun gotoDetailPage(imageView: ImageView, id: Long)
}
