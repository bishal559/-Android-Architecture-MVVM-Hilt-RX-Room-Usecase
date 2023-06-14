package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.R
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.common.loadImage
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.databinding.HolderPhotoBinding
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Photo

/**
 * [android.support.v7.widget.RecyclerView.Adapter] to adapt
 * [Photo] items into [RecyclerView] via [PhotoViewHolder]
 *
 *
 * Created by bishal on 12/06/2023
 */
internal class PhotosAdapter(val mListener: OnPhotosAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val photos: MutableList<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPhotoBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_photo, parent, false
        )
        return PhotoViewHolder(holderPhotoBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Photo {
        return photos[position]
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun addData(list: List<Photo>) {
        this.photos.clear()
        this.photos.addAll(list)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(photo: Photo) {
            val holderPhotoBinding = dataBinding as HolderPhotoBinding
            with(holderPhotoBinding) {
                photoViewModel = PhotoViewModel(photo)
                photoProgressBar.visibility = View.VISIBLE
                photoImageView.loadImage(photo.url, holderPhotoBinding.photoProgressBar)
            }

            itemView.setOnClickListener {
                mListener.gotoDetailPage(holderPhotoBinding.photoImageView, photo.id)
            }
        }
    }
}