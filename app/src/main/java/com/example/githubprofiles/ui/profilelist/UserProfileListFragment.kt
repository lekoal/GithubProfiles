package com.example.githubprofiles.ui.profilelist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.databinding.FragmentUserProfileListBinding
import com.example.githubprofiles.utils.ViewBindingFragment

class UserProfileListFragment :
    ViewBindingFragment<FragmentUserProfileListBinding>(FragmentUserProfileListBinding::inflate) {

    private val viewModel: UserProfileListViewModel by lazy {
        ViewModelProvider(this)[UserProfileListViewModel::class.java]
    }

    companion object {
        fun newInstance() = UserProfileListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}