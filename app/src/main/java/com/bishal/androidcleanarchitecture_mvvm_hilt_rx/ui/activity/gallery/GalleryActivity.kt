package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity.gallery

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.R
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.databinding.ActivityGalleryBinding
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.domain.model.Album
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity.BaseActivity
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity.detailphoto.PhotoDetailActivity
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment.album.AlbumsFragment
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment.photo.PhotosFragment
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.gallery.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.BR

@AndroidEntryPoint
class GalleryActivity : BaseActivity<ActivityGalleryBinding>(), OnGalleryCallback {
    override val layoutRes = R.layout.activity_gallery
    override val bindingVariable = BR.viewModel
    override val viewModel: GalleryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        if (savedInstanceState == null) {
            navigateToGalleryPage()
        }
    }

    private fun navigateToGalleryPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                AlbumsFragment.newInstance(),
                AlbumsFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

    override fun navigateToAlbumPage(album: Album) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                PhotosFragment.newInstance(album.id),
                PhotosFragment.FRAGMENT_NAME
            )
            .addToBackStack(PhotosFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }

    override fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long) {
        val intent = Intent(this, PhotoDetailActivity::class.java)
        val bundle = Bundle().apply {
            putLong(KEY_PHOTO_ID, id)
        }
        intent.putExtras(bundle)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            ViewCompat.getTransitionName(imageView) ?: ""
        )
        startActivity(intent, options.toBundle())
    }

    companion object {
        private const val KEY_PHOTO_ID = "KEY_PHOTO_ID"
    }
}