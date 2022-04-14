package com.example.githubprofiles.ui.profiledetails

import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.databinding.FragmentUserProfileDetailsBinding
import com.example.githubprofiles.utils.ViewBindingFragment

class UserProfileDetailsFragment :
    ViewBindingFragment<FragmentUserProfileDetailsBinding>
        (FragmentUserProfileDetailsBinding::inflate) {

    private val viewModel: UserProfileDetailsViewModel by lazy {
        ViewModelProvider(this)[UserProfileDetailsViewModel::class.java]
    }

    companion object {
        fun newInstance() = UserProfileDetailsFragment()
    }


}