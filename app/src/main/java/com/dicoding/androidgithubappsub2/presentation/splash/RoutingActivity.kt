package com.dicoding.androidgithubappsub2.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.dicoding.androidgithubappsub2.R
import com.dicoding.androidgithubappsub2.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class RoutingActivity : AppCompatActivity() {

    private val viewModel by viewModels<RoutingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routing)

        viewModel.isDarkMode().observe(this) { isDarkMode ->
            AppCompatDelegate.setDefaultNightMode(
                if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )

            lifecycleScope.launch {
                delay(2.seconds)
                val intent = Intent(this@RoutingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}