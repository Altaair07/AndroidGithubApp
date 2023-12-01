package com.dicoding.androidgithubappsub2.data.repository

import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun setDarkMode(isDarkMode: Boolean)
    fun isDarkMode(): Flow<Boolean>
}