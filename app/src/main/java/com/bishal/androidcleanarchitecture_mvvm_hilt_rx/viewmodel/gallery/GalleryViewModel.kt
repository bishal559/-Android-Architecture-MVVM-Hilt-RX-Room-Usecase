package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.gallery


import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.persistence.SharedPrefs
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
class GalleryViewModel @Inject constructor(
    private val sharedPrefs: SharedPrefs
) : BaseViewModel() {


}