package com.okala.repository.user

import androidx.lifecycle.LiveData
import com.okala.model.User
import com.okala.repository.utils.Resource

interface UserRepository {
    suspend fun getTopUsersWithCache(forceRefresh: Boolean = false):LiveData<Resource<List<User>>>
    suspend fun getUserDetailWithCache(forceRefresh: Boolean = false, login: String): LiveData<Resource<User>>
}