package com.okala.storeroom.di

import com.okala.featureshome.di.featureHomeModule
import com.okala.local.di.localModule
import com.okala.remote.di.createRemoteModule
import com.okala.repository.di.repositoryModule

val appComponent = listOf(createRemoteModule("https://api.github.com/"), repositoryModule,
    featureHomeModule, localModule)