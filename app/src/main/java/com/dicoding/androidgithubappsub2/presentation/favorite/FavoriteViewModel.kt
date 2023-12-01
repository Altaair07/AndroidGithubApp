package com.dicoding.androidgithubappsub2.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.androidgithubappsub2.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val favoriteUsers = userRepository.getAllFavoriteUsers().asLiveData()
}