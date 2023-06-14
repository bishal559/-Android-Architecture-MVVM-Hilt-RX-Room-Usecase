package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.usecase.GetPhotoDetailUseCase
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A helper class for the UI controller that is responsible for
 * preparing data for [PhotoDetailActivity]
 *
 * Created by bishal on 12/06/2023
 * */
@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
) : BaseViewModel() {

    val photoData = MutableLiveData<Photo>()
    val isLoad = MutableLiveData<Boolean>()
    val isFavorite = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    fun getDetail(id: Long?) {
        if (id == null) return
        getPhotoDetailUseCase.savePhotoId(id)
        getPhotoDetailUseCase.execute(
            onSuccess = {
                isLoad.value = true
                photoData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun updateFavoriteStatus() {
        photoData.value?.let { photo ->
            if (isFavorite.value == true) {
                getPhotoDetailUseCase.deleteAsFavorite(photo)
            } else {
                getPhotoDetailUseCase.addAsFavorite(photo)
            }
            isFavorite.value?.let {
                isFavorite.value = !it
            }
        }
    }

    fun checkFavoriteStatus(photoId: Long) {
        isFavorite.value = getPhotoDetailUseCase.isFavorite(photoId)
    }
}