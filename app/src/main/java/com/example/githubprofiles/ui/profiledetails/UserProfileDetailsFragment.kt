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
import com.example.githubprofiles.databinding.FragmentUserProfileDetailsBinding
import com.example.githubprofiles.repo.UserProfileDetailsRecyclerAdapter
import com.example.githubprofiles.repo.datasource.GitHubUserProfileDetailsDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileRepoListItemDTO
import com.example.githubprofiles.ui.AppState
import com.example.githubprofiles.utils.ViewBindingFragment

const val USER_PROFILE_DATA = "USER_PROFILE_DATA"

class UserProfileDetailsFragment :
    ViewBindingFragment<FragmentUserProfileDetailsBinding>
        (FragmentUserProfileDetailsBinding::inflate) {

    private val viewModel: UserProfileDetailsViewModel by lazy {
        ViewModelProvider(this)[UserProfileDetailsViewModel::class.java]
    }

    companion object {
        fun newInstance(bundle: Bundle): UserProfileDetailsFragment {
            val fragment = UserProfileDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile details & repo list"

        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getUserProfileDetails(this.arguments?.getString(USER_PROFILE_DATA))
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.LoadingUserProfileRepoSuccess -> {
                val userProfileRepoList = appState.getUserProfileRepoList
                val userProfileDetails = appState.getUserProfileDetailsData
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
        userProfileDetailsData: GitHubUserProfileDetailsDTO?,
        userProfileRepoList: List<GitHubUserProfileRepoListItemDTO>?
    ) {
        val rvUserProfileRepoList = binding.rvUserProfileDetailsRepos

        binding.userProfileAvatarLoad.load(
            userProfileDetailsData?.avatarUrl
        ) {
            precision(Precision.EXACT)
            error(R.drawable.ic_github)
            scale(Scale.FILL)
        }

        binding.userProfileNameLoad.text = userProfileDetailsData?.name
        binding.userProfileDetailsEmailLoad.text = userProfileDetailsData?.email.toString()
        binding.userProfileDetailsCreationDate.text = userProfileDetailsData?.createdAt

        rvUserProfileRepoList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvUserProfileRepoList.adapter = UserProfileDetailsRecyclerAdapter(
            userProfileRepoList
        )
    }

    override fun onPause() {
        super.onPause()
        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile list"
    }
}