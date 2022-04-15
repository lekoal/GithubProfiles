package com.example.githubprofiles.ui.profiledetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.R
import com.example.githubprofiles.databinding.FragmentUserProfileDetailsBinding
import com.example.githubprofiles.utils.ViewBindingFragment

const val USER_PROFILE_DATA = "USER_PROFILE_DATA"

class UserProfileDetailsFragment :
    ViewBindingFragment<FragmentUserProfileDetailsBinding>
        (FragmentUserProfileDetailsBinding::inflate) {

    private val viewModel: UserProfileDetailsViewModel by lazy {
        ViewModelProvider(this)[UserProfileDetailsViewModel::class.java]
    }

    companion object {
        fun newInstance() = UserProfileDetailsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile details & repo list"
    }

}