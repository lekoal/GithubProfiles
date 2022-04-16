package com.example.githubprofiles.ui.profiledetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.example.githubprofiles.R
import com.example.githubprofiles.databinding.FragmentProfileDetailsBinding
import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO
import com.example.githubprofiles.ui.AppState
import com.example.githubprofiles.utils.ViewBindingFragment

const val USER_PROFILE_DATA = "USER_PROFILE_DATA"

class ProfileDetailsFragment :
    ViewBindingFragment<FragmentProfileDetailsBinding>
        (FragmentProfileDetailsBinding::inflate) {

    private val viewModel: ProfileDetailsViewModel by lazy {
        ViewModelProvider(this)[ProfileDetailsViewModel::class.java]
    }

    companion object {
        fun newInstance(bundle: Bundle): ProfileDetailsFragment {
            val fragment = ProfileDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile details & repo list"


    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.LoadingUserProfileRepoSuccess -> {
                val userProfileRepoList = appState.getProfileRepoList
                val userProfileDetails = appState.getProfileDetailsData
                binding.loadingProcessLayout.visibility = View.GONE
                setData(userProfileDetails, userProfileRepoList)
            }
            is AppState.Loading -> {
                binding.loadingProcessLayout.visibility = View.VISIBLE
            }
            else -> {
                binding.loadingProcessLayout.visibility = View.GONE
                Toast.makeText(requireContext(), "Error loading data!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setData(
        profileDetailsData: GitHubProfileDetailsDTO?,
        profileRepoList: List<GitHubProfileRepoListItemDTO>?
    ) {
        val rvUserProfileRepoList = binding.rvProfileReposLoad

        binding.profileAvatarLoad.load(
            profileDetailsData?.avatarUrl
        ) {
            precision(Precision.EXACT)
            error(R.drawable.ic_github)
            scale(Scale.FILL)
        }

        binding.profileNameLoad.text = profileDetailsData?.name
        binding.profileEmailLoad.text = profileDetailsData?.email.toString()
        binding.profileCreationDateLoad.text = profileDetailsData?.createdAt

        rvUserProfileRepoList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvUserProfileRepoList.adapter = ProfileDetailsRecyclerAdapter(
            profileRepoList
        )
    }

    override fun onPause() {
        super.onPause()
        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile list"
    }
}