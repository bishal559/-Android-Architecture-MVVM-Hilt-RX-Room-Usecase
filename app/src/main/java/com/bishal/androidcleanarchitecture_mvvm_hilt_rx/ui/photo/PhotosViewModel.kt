package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**A helper class for the UI controller that is responsible for
 * preparing data for the UI [PhotosFragment]
 *
 * Created by bishal on 12/06/2023
 * */
@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    val photoListReceivedLiveData = MutableLiveData<List<Photo>>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    fun loadPhotos(id: Long?) {
        if (id == null) return
        getPhotosUseCase.saveAlbumId(id)
        getPhotosUseCase.execute(
            onSuccess = {
                isLoad.value = true
                photoListReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    companion object {
        private val TAG = PhotosViewModel::class.java.simpleName
    }
}
