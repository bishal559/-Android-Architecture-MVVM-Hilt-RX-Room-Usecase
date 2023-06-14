package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment.album

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.BR
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.R
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.databinding.FragmentAlbumsBinding
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity.gallery.OnGalleryCallback
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment.BaseFragment
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.albums.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : BaseFragment<FragmentAlbumsBinding>(), OnAlbumsAdapterListener {

    private var adapter: AlbumsAdapter? = null
    private var mCallback: OnGalleryCallback? = null


    override val layoutRes: Int = R.layout.fragment_albums
    override val bindingVariable = BR.viewModel
    override val viewModel: AlbumsViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnGalleryCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnGalleryCallback!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = AlbumsAdapter(this)
        viewModel.loadAlbums()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.albumsRecyclerView.adapter = adapter

        viewModel.isLoad.observe(
            viewLifecycleOwner
        ) {
            it?.let { visibility ->
                dataBinding.albumsProgressBar.visibility =
                    if (visibility) View.GONE else View.VISIBLE
            }
        }

        viewModel.albumsReceivedLiveData.observe(
            viewLifecycleOwner
        ) {
            it?.let {
                initRecyclerView(it)
            }
        }
    }


    override fun showPhotos(album: Album) {
        mCallback?.navigateToAlbumPage(album)
    }

    private fun initRecyclerView(albums: List<Album>) {
        adapter?.addData(albums)
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
        mCallback = null
    }

    companion object {

        val FRAGMENT_NAME = AlbumsFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            AlbumsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}