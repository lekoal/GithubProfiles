package com.example.githubprofiles.ui.profilelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubprofiles.R
import com.example.githubprofiles.app
import com.example.githubprofiles.databinding.FragmentProfileListBinding
import com.example.githubprofiles.ui.profiledetails.ProfileDetailsFragment
import com.example.githubprofiles.ui.profiledetails.USER_PROFILE_DATA
import io.reactivex.rxjava3.disposables.Disposable

class ProfileListFragment : Fragment(R.layout.fragment_profile_list) {

    private var _binding: FragmentProfileListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileListViewModel by activityViewModels {
        ProfileListViewModelFactory(
            requireContext()
                .app
                .gitHubGetUsersData
        )
    }

    private val adapter = ProfileListRecyclerAdapter()

    private var subscribe: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileListBinding.bind(view)

        rvInit()
        viewModel.getUserProfileList()
        eventsHandler()
        setupItemClick()
    }

    private fun rvInit() {
        binding.rvProfileListLoad.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProfileListLoad.adapter = adapter
    }

    private fun eventsHandler() {
        viewModel.profiles.observe(requireActivity()) {
            adapter.setData(it)
        }
        viewModel.onError.observe(requireActivity()) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
        viewModel.inProgress.observe(requireActivity()) { inProgress ->
            binding.profileListLoadingProcessLayout.isVisible = inProgress
            binding.rvProfileListLoad.isEnabled = !inProgress
        }
    }

    private fun setupItemClick() {
        val bundle = Bundle()
        val manager = activity?.supportFragmentManager
        subscribe = adapter.clickEvent
            .subscribe {
                bundle.putString(USER_PROFILE_DATA, it)
                manager?.beginTransaction()
                    ?.replace(R.id.details_container, ProfileDetailsFragment.newInstance(bundle))
                    ?.addToBackStack("")
                    ?.commit()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscribe?.dispose()
        _binding = null
    }
}