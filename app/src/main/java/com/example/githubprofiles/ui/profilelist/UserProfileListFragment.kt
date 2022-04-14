package com.example.githubprofiles.ui.profilelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.R
import com.example.githubprofiles.databinding.FragmentUserProfileListBinding
import com.example.githubprofiles.repo.datasource.GitHubUserProfileListItemDTO
import com.example.githubprofiles.ui.AppState
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

        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile list"

        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun renderData(appState: AppState?) {
        when(appState) {
            is AppState.LoadingUserProfileListSuccess -> {
                val userProfileList = appState.getUserProfileListData
                binding.loadingProcessLayout.visibility = View.GONE
                setData(userProfileList)
            }
            is AppState.Loading -> {
                binding.loadingProcessLayout.visibility = View.VISIBLE
            }
            else -> {
                binding.loadingProcessLayout.visibility = View.GONE
                Toast.makeText(requireContext(), "Error loading data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setData(userProfileList: List<GitHubUserProfileListItemDTO>?) {
        val manager = activity?.supportFragmentManager
        val rvUserProfileList = binding.rvUserProfileList


    }

}