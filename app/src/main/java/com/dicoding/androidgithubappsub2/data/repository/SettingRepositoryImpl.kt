package com.dicoding.androidgithubappsub2.data.repository

import com.dicoding.androidgithubappsub2.data.local.datastore.SettingPref
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepositoryImpl @Inject constructor(
    private val settingPref: SettingPref
) : SettingRepository {

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        settingPref.setDarkMode(isDarkMode)
    }

    override fun isDarkMode(): Flow<Boolean> {
        return settingPref.isDarkMode()
    }
}