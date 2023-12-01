package com.dicoding.androidgithubappsub2.presentation.detail.follow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.dicoding.androidgithubappsub2.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val username = MutableLiveData<String>()

    fun setUsername(username: String) {
        this.username.value = username
    }

    val following = username.switchMap {
        userRepository.getFollowings(it).asLiveData()
    }

    val followers = username.switchMap {
        userRepository.getFollowers(it).asLiveData()
    }
}