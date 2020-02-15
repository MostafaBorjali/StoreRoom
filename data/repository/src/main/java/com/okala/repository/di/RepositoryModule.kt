package com.okala.repository.di

import com.okala.repository.AppDispatchers
import com.okala.repository.user.UserRepository
import com.okala.repository.user.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main,Dispatchers.IO) }
    factory { UserRepositoryImpl(get(),get()) as UserRepository }
}