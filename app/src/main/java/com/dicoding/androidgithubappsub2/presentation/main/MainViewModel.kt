package com.dicoding.androidgithubappsub2.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.dicoding.androidgithubappsub2.data.remote.response.SearchResponse
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import com.dicoding.androidgithubappsub2.data.repository.UserRepository
import com.dicoding.androidgithubappsub2.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val username = MutableLiveData<String>()

    fun setUsername(username: String) {
        this.username.value = username
    }

    val allUsers: LiveData<Result<List<UserResponse>>> by lazy {
        userRepository.getAllUsers().asLiveData()
    }

    val searchUser: LiveData<Result<SearchResponse>> = username.switchMap {
        userRepository.searchUser(it).asLiveData()
    }
}