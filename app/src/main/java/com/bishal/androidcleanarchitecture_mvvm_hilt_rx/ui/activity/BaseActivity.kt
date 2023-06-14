package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.common.hideProgressDialog
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.common.showProgressDialog
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.persistence.SharedPrefs
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.base.BaseViewModel
import javax.inject.Inject


abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {


    @Inject
    lateinit var  sharedPrefs: SharedPrefs




    protected lateinit var dataBinding:VDB



    @get:LayoutRes
    abstract val layoutRes: Int

    abstract val bindingVariable: Int

    abstract val viewModel: ViewModel







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.lifecycleOwner = this
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()


        // observe title event
        observeTitleEvent()
        // observe api progress event
        observeApiProgressEvent()
    }



    private fun observeTitleEvent() {
        (viewModel as BaseViewModel).title.observe(this) {
            setTitle(it)
        }
    }

    private fun observeApiProgressEvent() {
        (viewModel as BaseViewModel).apiRequestInProgress.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }
    }

    private fun setTitle(title: String) {
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(true)
            it.setLogo(null)
            it.title = title
        }
    }



}