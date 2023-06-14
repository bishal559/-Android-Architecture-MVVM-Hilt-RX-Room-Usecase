package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.album

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.R
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.databinding.FragmentAlbumsBinding
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.gallery.OnGalleryCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment(), OnAlbumsAdapterListener {

    private lateinit var fragmentAlbumsBinding: FragmentAlbumsBinding
    private var adapter: AlbumsAdapter? = null
    private var mCallback: OnGalleryCallback? = null

    private val viewModel: AlbumsViewModel by viewModels()

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAlbumsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_albums, container, false)
        fragmentAlbumsBinding.albumsViewModel = viewModel
        fragmentAlbumsBinding.albumsRecyclerView.adapter = adapter

        viewModel.isLoad.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { visibility ->
                    fragmentAlbumsBinding.albumsProgressBar.visibility = if (visibility) View.GONE else View.VISIBLE
                }
            }
        )

        viewModel.albumsReceivedLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    initRecyclerView(it)
                }
            }
        )

        return fragmentAlbumsBinding.root
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