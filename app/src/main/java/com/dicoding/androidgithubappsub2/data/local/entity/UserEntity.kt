package com.dicoding.androidgithubappsub2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val avatarUrl: String?,
    val login: String?,
)