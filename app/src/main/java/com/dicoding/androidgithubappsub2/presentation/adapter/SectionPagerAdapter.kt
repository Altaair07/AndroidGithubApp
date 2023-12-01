package com.dicoding.androidgithubappsub2.presentation.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.androidgithubappsub2.presentation.detail.follow.FollowFragment

class SectionPagerAdapter(activity: AppCompatActivity, private val username: String) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle().apply {
            putInt(FollowFragment.EXTRA_POSITION, position)
            putString(FollowFragment.EXTRA_USERNAME, username)
        }
        val fragment = FollowFragment().apply {
            arguments = bundle
        }
        return fragment
    }
}