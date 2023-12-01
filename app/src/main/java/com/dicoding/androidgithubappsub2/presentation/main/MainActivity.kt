package com.dicoding.androidgithubappsub2.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.androidgithubappsub2.R
import com.dicoding.androidgithubappsub2.data.remote.response.SearchResponse
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import com.dicoding.androidgithubappsub2.databinding.ActivityMainBinding
import com.dicoding.androidgithubappsub2.presentation.adapter.UserAdapter
import com.dicoding.androidgithubappsub2.presentation.detail.DetailActivity
import com.dicoding.androidgithubappsub2.presentation.favorite.FavoriteActivity
import com.dicoding.androidgithubappsub2.presentation.setting.SettingActivity
import com.dicoding.androidgithubappsub2.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSearchBar()
        setUpRecyclerView()

        viewModel.allUsers.observe(this, userObserver)
        viewModel.searchUser.observe(this@MainActivity, searchObserver)
    }

    private fun setUpSearchBar() {
        with(binding) {
            setSupportActionBar(toolbar)
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                viewModel.setUsername(searchView.text.toString())
                searchView.hide()
                false
            }
        }
    }

    private fun setUpRecyclerView() {
        userAdapter = UserAdapter(
            onClick = {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_USERNAME, it.login)
                }
                startActivity(intent)
            }
        )
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private val userObserver = Observer<Result<List<UserResponse>>> { result ->
        when (result) {
            is Result.Error -> {
                showLoading(false)
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
            is Result.Loading -> {
                showLoading(true)
            }
            is Result.Success -> {
                showLoading(false)
                userAdapter.submitList(result.data)
                binding.textEmpty.isVisible = result.data.isNullOrEmpty()
            }
        }
    }

    private val searchObserver = Observer<Result<SearchResponse>> { result ->
        when (result) {
            is Result.Error -> {
                showLoading(false)
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
            is Result.Loading -> {
                showLoading(true)
            }
            is Result.Success -> {
                showLoading(false)
                userAdapter.submitList(result.data?.items)
                binding.textEmpty.isVisible = result.data?.items.isNullOrEmpty()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            pbMain.isVisible = isLoading
            rvUser.isVisible = !isLoading
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.btn_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}