package com.dicoding.androidgithubappsub2.data.remote.retrofit

import com.dicoding.androidgithubappsub2.data.remote.response.DetailResponse
import com.dicoding.androidgithubappsub2.data.remote.response.SearchResponse
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") username: String
    ): SearchResponse

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): DetailResponse

    @GET("users/{username}/following")
    suspend fun getFollowings(
        @Path("username") username: String
    ): List<UserResponse>

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String
    ): List<UserResponse>
}