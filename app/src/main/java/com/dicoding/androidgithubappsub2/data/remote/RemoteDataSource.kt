package com.dicoding.androidgithubappsub2.data.remote

import com.dicoding.androidgithubappsub2.data.remote.response.DetailResponse
import com.dicoding.androidgithubappsub2.data.remote.response.SearchResponse
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import com.dicoding.androidgithubappsub2.data.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllUsers(): List<UserResponse> {
        return apiService.getAllUsers()
    }

    suspend fun searchUser(username: String): SearchResponse {
        return apiService.searchUser(username)
    }

    suspend fun getDetailUser(username: String): DetailResponse {
        return apiService.getDetailUser(username)
    }

    suspend fun getFollowings(username: String): List<UserResponse> {
        return apiService.getFollowings(username)
    }

    suspend fun getFollowers(username: String): List<UserResponse> {
        return apiService.getFollowers(username)
    }
}