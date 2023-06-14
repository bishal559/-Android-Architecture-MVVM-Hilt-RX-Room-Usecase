package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity.detailphoto

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.BR
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.R
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.common.loadImageFull
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.databinding.ActivityPhotoDetailBinding
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity.BaseActivity
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.photos.PhotoDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailActivity : BaseActivity<ActivityPhotoDetailBinding>() {

    override val layoutRes = R.layout.activity_photo_detail
    override val bindingVariable = BR.viewModel
    override val viewModel: PhotoDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dataBinding.photoDetailViewModel = viewModel

        val photoId = intent?.extras?.getLong(KEY_PHOTO_ID) ?: return

        with(viewModel) {
            getDetail(photoId)
            checkFavoriteStatus(photoId)

            photoData.observe(
                this@PhotoDetailActivity
            ) {
                dataBinding.detailTitleTextView.text = it?.title
                dataBinding.detailToolbarImageView.loadImageFull(it?.url)
            }

            isFavorite.observe(
                this@PhotoDetailActivity
            ) {
                it?.let {
                    dataBinding.detailFab.setImageResource(
                        if (it) R.drawable.ic_star_full_vector else R.drawable.ic_star_empty_white_vector
                    )
                }
            }

            dataBinding.detailFab.setOnClickListener {
                updateFavoriteStatus()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val KEY_PHOTO_ID = "KEY_PHOTO_ID"
    }
}