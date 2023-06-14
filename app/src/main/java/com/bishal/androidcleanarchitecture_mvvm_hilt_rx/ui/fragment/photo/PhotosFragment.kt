package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment.photo

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.BR
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.R
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.databinding.FragmentPhotosBinding
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity.gallery.OnGalleryCallback
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment.BaseFragment
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.photos.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding>(), OnPhotosAdapterListener {

    private var adapter: PhotosAdapter? = null
    private var mCallback: OnGalleryCallback? = null


    override val layoutRes: Int = R.layout.fragment_photos
    override val bindingVariable = BR.viewModel
    override val viewModel: PhotosViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnGalleryCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnGalleryCallback!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PhotosAdapter(this)
        val albumId = arguments?.let { it.getLong(KEY_ALBUM_ID) }
        viewModel.loadPhotos(albumId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.photosRecyclerView.adapter = adapter

        viewModel.isLoad.observe(
            viewLifecycleOwner
        ) {
            it?.let { visibility ->
                dataBinding.photosProgressBar.visibility =
                    if (visibility) View.GONE else View.VISIBLE
            }
        }

        viewModel.photoListReceivedLiveData.observe(
            viewLifecycleOwner
        ) {
            it?.let {
                adapter?.addData(it)
            }
        }
    }

    override fun gotoDetailPage(imageView: ImageView, id: Long) {
        mCallback?.gotoDetailPageByPhotoId(imageView, id)
    }

    override fun onDetach() {
        super.onDetach()
        mCallback = null
        adapter = null
    }

    companion object {

        val KEY_ALBUM_ID = "KEY_ALBUM_ID"
        val FRAGMENT_NAME = PhotosFragment::class.java.name

        @JvmStatic
        fun newInstance(id: Long) = PhotosFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_ALBUM_ID, id)
            }
        }
    }
}