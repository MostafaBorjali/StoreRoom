package com.okala.repository.user

import androidx.lifecycle.LiveData
import com.okala.model.ApiResult
import com.okala.model.User
import com.okala.remote.UserDatasource
import com.okala.repository.utils.NetworkBoundResource
import com.okala.repository.utils.Resource
import com.okala.local.dao.UserDao
import kotlinx.coroutines.Deferred

class UserRepositoryImpl(
    private val datasource: UserDatasource,
    private val dao: UserDao
) : UserRepository {
    override suspend fun getTopUsersWithCache(forceRefresh: Boolean): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, ApiResult<User>>() {
            override fun processResponse(response: ApiResult<User>): List<User> {
                return response.items
            }

            override suspend fun saveCallResults(items: List<User>) {
                return dao.save(items)
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return data == null || data.isEmpty() || forceRefresh
            }

            override suspend fun loadFromDb(): List<User> = dao.getTopUsers()

            override fun createCallAsync(): Deferred<ApiResult<User>> =
                datasource.fetchTopUsersAsync()

        }.build().asLiveData()
    }

    override suspend fun getUserDetailWithCache(
        forceRefresh: Boolean,
        login: String
    ): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>() {

            override fun processResponse(response: User): User = response

            override suspend fun saveCallResults(item: User) = dao.save(item)

            override fun shouldFetch(data: User?): Boolean = data == null
                    || data.haveToRefreshFromNetwork()
                    || data.name.isNullOrEmpty()
                    || forceRefresh

            override suspend fun loadFromDb(): User = dao.getUser(login)

            override fun createCallAsync(): Deferred<User> = datasource.fetchUserDetailsAsync(login)

        }.build().asLiveData()
    }

}