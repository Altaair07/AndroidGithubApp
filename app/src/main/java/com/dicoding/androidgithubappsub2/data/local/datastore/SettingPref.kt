package com.dicoding.androidgithubappsub2.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.dicoding.androidgithubappsub2.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingPref @Inject constructor(private val dataStore: DataStore<Preferences>) {

    fun isDarkMode(): Flow<Boolean> {
        return dataStore.data.map {
            it[Constants.darkModeKey] ?: false
        }
    }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit {
            it[Constants.darkModeKey] = isDarkMode
        }
    }
}