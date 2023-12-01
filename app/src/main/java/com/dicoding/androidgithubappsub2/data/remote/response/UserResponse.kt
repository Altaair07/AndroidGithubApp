package com.dicoding.androidgithubappsub2.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("login")
    val login: String? = null,
)