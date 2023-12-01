package com.dicoding.androidgithubappsub2.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.dicoding.androidgithubappsub2.R
import com.dicoding.androidgithubappsub2.data.remote.response.DetailResponse
import com.dicoding.androidgithubappsub2.databinding.ActivityDetailBinding
import com.dicoding.androidgithubappsub2.presentation.adapter.SectionPagerAdapter
import com.dicoding.androidgithubappsub2.util.Result
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra(EXTRA_USERNAME)
        viewModel.setUsername(username ?: "")

        setUpToolbar()
        setUpPagerTabs()

        viewModel.detailUser.observe(this) { result ->
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
                    populateUser(result.data)
                }
            }
        }
    }

    private fun populateUser(user: DetailResponse?) {
        with(binding) {
            tvFollowers.text = user?.followers.toString()
            tvFollowing.text = user?.following.toString()
            tvNameDetail.text = user?.name

            Glide.with(this@DetailActivity)
                .load(user?.avatarUrl)
                .into(ivUserDetail)

            viewModel.isFavoriteUser().observe(this@DetailActivity) { isFavorite ->
                btnFavorite.setImageResource(
                    if (isFavorite) R.drawable.ic_favorite_filled
                    else R.drawable.ic_favorite_border
                )
                btnFavorite.setOnClickListener {
                    if (isFavorite) {
                        viewModel.deleteFromFavorite(username ?: "")
                        Snackbar.make(
                            binding.btnFavorite,
                            "${user?.login} dihapus dari Favorite",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.addToFavorite(user)
                        Snackbar.make(
                            binding.btnFavorite,
                            "Berhasil menambahkan ${user?.login} ke Favorite",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun setUpToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.title = username
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setUpPagerTabs() {
        val pagerAdapter = SectionPagerAdapter(this, username ?: "")
        with(binding) {
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            pbDetail.isVisible = isLoading
            textFollowers.isVisible = !isLoading
            textFollowings.isVisible = !isLoading
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_USERNAME = "username"
        @StringRes
        val TAB_TITLES = arrayOf(R.string.followers, R.string.following)
    }
}