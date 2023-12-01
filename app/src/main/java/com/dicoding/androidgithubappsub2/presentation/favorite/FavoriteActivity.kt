package com.dicoding.androidgithubappsub2.presentation.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import com.dicoding.androidgithubappsub2.databinding.ActivityFavoriteBinding
import com.dicoding.androidgithubappsub2.presentation.adapter.UserAdapter
import com.dicoding.androidgithubappsub2.presentation.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = UserAdapter(
            onClick = {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_USERNAME, it.login)
                }
                startActivity(intent)
            }
        )

        setUpToolbar()

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
        viewModel.favoriteUsers.observe(this) { result ->
            val userResponse = result.map {
                UserResponse(
                    avatarUrl = it.avatarUrl,
                    login = it.login
                )
            }
            userAdapter.submitList(userResponse)
            binding.textEmpty.isVisible = result.isNullOrEmpty()
        }
    }

    private fun setUpToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}