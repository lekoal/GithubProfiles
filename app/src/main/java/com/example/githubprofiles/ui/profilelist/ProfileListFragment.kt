package com.example.githubprofiles.ui.profilelist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubprofiles.R
import com.example.githubprofiles.app
import com.example.githubprofiles.databinding.FragmentProfileListBinding
import com.example.githubprofiles.utils.ViewBindingFragment


class ProfileListFragment :
    ViewBindingFragment<FragmentProfileListBinding>(FragmentProfileListBinding::inflate) {

    private val viewModel: ProfileListViewModel by activityViewModels { ProfileViewModelFactory(activity?.applicationContext?.app?.gitHubGetUsersData) }
    private val adapter = ProfileListRecyclerAdapter()

    companion object {
        fun newInstance() = ProfileListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile list"

        rvInit()
        viewModel.getUserProfileList()
        eventsHandler()
    }

    private fun rvInit() {
        binding.rvProfilesLoad.layoutManager = LinearLayoutManager(context)
        binding.rvProfilesLoad.adapter = adapter
    }

    private fun eventsHandler() {
        viewModel.profiles.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.inProgress.observe(viewLifecycleOwner) { inProgress ->
            binding.loadingProcessLayout.isVisible = inProgress
            binding.rvProfilesLoad.isEnabled = !inProgress
        }
    }

}