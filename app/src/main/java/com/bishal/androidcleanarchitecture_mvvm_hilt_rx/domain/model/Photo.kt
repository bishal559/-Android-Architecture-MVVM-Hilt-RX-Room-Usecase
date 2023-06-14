package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model

data class Photo(
    var id: Long,
    var title: String,
    val url: String,
    val thumbnailUrl: String?
) {
    fun setName(text: String) {
        title = text
    }
}
