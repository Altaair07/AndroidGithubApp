package com.dicoding.androidgithubappsub2.data.repository

import com.dicoding.androidgithubappsub2.data.local.entity.UserEntity
import com.dicoding.androidgithubappsub2.data.remote.response.DetailResponse
import com.dicoding.androidgithubappsub2.data.remote.response.SearchResponse
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import com.dicoding.androidgithubappsub2.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<Result<List<UserResponse>>>
    fun searchUser(username: String): Flow<Result<SearchResponse>>
    fun getDetailUser(username: String): Flow<Result<DetailResponse>>
    fun getFollowings(username: String): Flow<Result<List<UserResponse>>>
    fun getFollowers(username: String): Flow<Result<List<UserResponse>>>
    suspend fun addToFavorite(userEntity: UserEntity)
    fun getAllFavoriteUsers(): Flow<List<UserEntity>>
    suspend fun deleteUser(username: String)
    fun isFavoriteUser(username: String): Flow<Boolean>
}