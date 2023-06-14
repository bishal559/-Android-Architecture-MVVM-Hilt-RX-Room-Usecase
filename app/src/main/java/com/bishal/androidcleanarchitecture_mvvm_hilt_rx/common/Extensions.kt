package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.common

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.Calendar

fun ImageView.loadImageFull(url: String?) =
    Picasso.get().load(url).into(this)

fun ImageView.loadImage(url: String, progressBar: ProgressBar) =
    Picasso.get()
        .load(url)
        .placeholder(android.R.color.white)
        .into(
            this,
            object : Callback {

                override fun onError(e: java.lang.Exception?) {
                    e?.printStackTrace()
                }

                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }
            }
        )

private var progressDialog: AlertDialog? = null
private var dialogDisplayedTimeInMillis: Long = 0
private const val preferredDelayInMillis: Long = 750
fun Activity.showProgressDialog() {
    progressDialog?.dismiss()
    val view = layoutInflater.inflate(R.layout.progress_dialog, null)
    progressDialog = AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(false)
        .create()
    progressDialog?.show()
        .also { dialogDisplayedTimeInMillis = Calendar.getInstance().timeInMillis }
    progressDialog?.window?.apply {
        setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawableResource(android.R.color.transparent)
    }
}
fun Fragment.showProgressDialog() {
    requireActivity().showProgressDialog()
}
fun hideProgressDialog() {
    val timeElapsed = Calendar.getInstance().timeInMillis - dialogDisplayedTimeInMillis
    if (timeElapsed > preferredDelayInMillis) {
        progressDialog?.dismiss()
    } else {
        if(progressDialog !=null)
            Handler(Looper.getMainLooper()).postDelayed({
                if(progressDialog?.isShowing == true)
                    try {
                        progressDialog?.dismiss()
                    } catch (ex: Exception) {
                        Log.e("TAG", ex.message, ex)
                    }


            }, preferredDelayInMillis - timeElapsed)
    }


}