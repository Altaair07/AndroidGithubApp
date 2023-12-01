package com.dicoding.androidgithubappsub2.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @field:SerializedName("items")
    val items: List<UserResponse>
)