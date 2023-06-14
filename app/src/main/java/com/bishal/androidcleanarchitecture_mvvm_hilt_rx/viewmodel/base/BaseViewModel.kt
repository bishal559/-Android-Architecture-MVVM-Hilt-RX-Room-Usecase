package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    val apiRequestInProgress = MutableLiveData<Boolean>()
    val title = MutableLiveData<String>()




    init {
        apiRequestInProgress.value = false
    }
}