package com.dicoding.androidgithubappsub2.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.dicoding.androidgithubappsub2.data.local.entity.UserEntity
import com.dicoding.androidgithubappsub2.data.remote.response.DetailResponse
import com.dicoding.androidgithubappsub2.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val username = MutableLiveData<String>()

    fun setUsername(username: String) {
        this.username.value = username
    }

    val detailUser = username.switchMap {
        userRepository.getDetailUser(it).asLiveData()
    }

    fun isFavoriteUser() = username.switchMap {
        userRepository.isFavoriteUser(it).asLiveData()
    }

    fun addToFavorite(detailResponse: DetailResponse?) = viewModelScope.launch {
        userRepository.addToFavorite(
            UserEntity(
                avatarUrl = detailResponse?.avatarUrl,
                login = detailResponse?.login
            )
        )
    }

    fun deleteFromFavorite(username: String) = viewModelScope.launch {
        userRepository.deleteUser(username)
    }
}