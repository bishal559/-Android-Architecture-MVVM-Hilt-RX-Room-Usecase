package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.common.hideProgressDialog
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.common.showProgressDialog
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.persistence.SharedPrefs
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.viewmodel.base.BaseViewModel
import javax.inject.Inject

abstract class BaseFragment< VDB : ViewDataBinding> : Fragment() {



    @Inject
    lateinit var  sharedPrefs: SharedPrefs

    protected lateinit var dataBinding: VDB

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int

    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
    }

}