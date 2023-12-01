package com.dicoding.androidgithubappsub2.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.androidgithubappsub2.data.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {

    fun isDarkMode() = settingRepository.isDarkMode().asLiveData()

    fun setDarkMode(isDarkMode: Boolean) = viewModelScope.launch {
        settingRepository.setDarkMode(isDarkMode)
    }
}