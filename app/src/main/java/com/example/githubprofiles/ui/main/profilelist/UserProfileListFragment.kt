package com.example.githubprofiles.ui.main.profilelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.R
import com.example.githubprofiles.databinding.FragmentUserProfileListBinding
import com.example.githubprofiles.utils.ViewBindingFragment

class UserProfileListFragment :
    ViewBindingFragment<FragmentUserProfileListBinding>(FragmentUserProfileListBinding::inflate) {

    companion object {
        fun newInstance() = UserProfileListFragment()
    }

    private val viewModel: UserProfileListViewModel by lazy {
        ViewModelProvider(this)[UserProfileListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}