package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo

class PhotoViewModel(val photo: Photo) : ViewModel() {

    val photoData = MutableLiveData<Photo>()

    init {
        photoData.value = photo
    }
}