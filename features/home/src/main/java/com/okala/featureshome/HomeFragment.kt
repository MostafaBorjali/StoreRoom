package com.okala.featureshome

import com.okala.common.base.BaseFragment
import com.okala.common.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = viewModel
}