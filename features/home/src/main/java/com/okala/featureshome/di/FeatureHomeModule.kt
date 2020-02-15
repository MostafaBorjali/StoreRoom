package com.okala.featureshome.di

import com.okala.featureshome.HomeViewModel
import com.okala.featureshome.domain.GetTopUsersUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureHomeModule = module {

    factory { GetTopUsersUseCase(get()) }
    viewModel { HomeViewModel(get(),get()) }
}