package com.example.githubprofiles.ui.profilelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ProfileListFragment : Fragment() {

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

    companion object {
        fun newInstance() = ProfileListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile list"

        rvInit()
        viewModel.getUserProfileList()
        eventsHandler()
        setupItemClick()
    }

    private fun rvInit() {
        binding.rvProfilesLoad.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProfilesLoad.adapter = adapter
    }

    private fun eventsHandler() {
        viewModel.profiles.observe(requireActivity()) {
            adapter.setData(it)
        }
        viewModel.inProgress.observe(requireActivity()) { inProgress ->
            binding.loadingProcessLayout.isVisible = inProgress
            binding.rvProfilesLoad.isEnabled = !inProgress
        }
    }

    private fun setupItemClick() {
        val bundle = Bundle()
        val manager = activity?.supportFragmentManager
        subscribe = adapter.clickEvent
            .subscribe {
                bundle.putString(USER_PROFILE_DATA, it)
                manager?.beginTransaction()
                    ?.replace(R.id.container, ProfileDetailsFragment.newInstance(bundle))
                    ?.addToBackStack("")
                    ?.commit()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscribe?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}