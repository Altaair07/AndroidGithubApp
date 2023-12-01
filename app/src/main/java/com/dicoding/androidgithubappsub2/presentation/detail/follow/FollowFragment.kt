package com.dicoding.androidgithubappsub2.presentation.detail.follow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import com.dicoding.androidgithubappsub2.databinding.FragmentFollowBinding
import com.dicoding.androidgithubappsub2.presentation.adapter.UserAdapter
import com.dicoding.androidgithubappsub2.presentation.detail.DetailActivity
import com.dicoding.androidgithubappsub2.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<FollowViewModel>()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(EXTRA_POSITION, 0) ?: 0
        val username = arguments?.getString(EXTRA_USERNAME) ?: ""

        setUpRecyclerView()

        when (position) {
            0 -> {
                viewModel.setUsername(username)
                viewModel.followers.observe(viewLifecycleOwner, followObserver)
            }
            1 -> {
                viewModel.setUsername(username)
                viewModel.following.observe(viewLifecycleOwner, followObserver)
            }
        }
    }

    private fun setUpRecyclerView() {
        userAdapter = UserAdapter(
            onClick = {
                val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_USERNAME, it.login)
                }
                startActivity(intent)
            }
        )
        binding?.rvUser?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private val followObserver = Observer<Result<List<UserResponse>>> { result ->
        when (result) {
            is Result.Error -> {
                showLoading(false)
                Toast.makeText(requireActivity(), result.message, Toast.LENGTH_SHORT).show()
            }
            is Result.Loading -> {
                showLoading(true)
            }
            is Result.Success -> {
                showLoading(false)
                userAdapter.submitList(result.data)
                binding?.textEmpty?.isVisible = result.data.isNullOrEmpty()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            pbFollow.isVisible = isLoading
            rvUser.isVisible = !isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_POSITION = "extra_position"
        const val EXTRA_USERNAME = "extra_username"
    }
}