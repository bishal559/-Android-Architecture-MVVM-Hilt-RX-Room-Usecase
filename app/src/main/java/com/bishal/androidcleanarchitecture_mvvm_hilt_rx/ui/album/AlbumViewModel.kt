package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.album

import androidx.lifecycle.MutableLiveData
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album

/**A helper class for the UI controller that is responsible for
 * preparing data for [AlbumViewModel] as the UI
 *
 * Created by bishal on 12/06/2023
 * */
class AlbumViewModel(val album: Album) {

    private val TAG = AlbumViewModel::class.java.simpleName
    val albumData = MutableLiveData<Album>()

    init {
        albumData.value = album
    }
}