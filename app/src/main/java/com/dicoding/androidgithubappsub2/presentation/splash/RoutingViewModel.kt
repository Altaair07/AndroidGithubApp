package com.dicoding.androidgithubappsub2.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.androidgithubappsub2.data.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {

    fun isDarkMode() = settingRepository.isDarkMode().asLiveData()
}