package com.example.githubprofiles.ui.profiledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.example.githubprofiles.R
import com.example.githubprofiles.app
import com.example.githubprofiles.databinding.FragmentProfileDetailsBinding

const val USER_PROFILE_DATA = "USER_PROFILE_DATA"

class ProfileDetailsFragment : Fragment() {

    private var _binding: FragmentProfileDetailsBinding? = null
    private val binding get() = _binding!!

    private var userLogin = ""

    private val viewModel: ProfileDetailsViewModel by activityViewModels {
        ProfileDetailsViewModelFactory(
            requireContext()
                .app
                .gitHubGetUsersData
        )
    }

    private val adapter = ProfileDetailsRecyclerAdapter()

    companion object {
        fun newInstance(bundle: Bundle): ProfileDetailsFragment {
            val fragment = ProfileDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile details & repo list"

        userLogin = this.arguments?.getString(USER_PROFILE_DATA).toString()

        rvInit()
        getData(userLogin)
        eventsHandler()
    }

    private fun getData(userLogin: String) {
        viewModel.getProfileRepos(userLogin)
        viewModel.getProfileDetails(userLogin)
    }

    private fun rvInit() {
        binding.rvProfileReposLoad.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProfileReposLoad.adapter = adapter
    }

    private fun eventsHandler() {
        viewModel.repos.observe(requireActivity()) {
            adapter.setData(it)
        }
        viewModel.profile.observe(requireActivity()) {
            binding.profileNameLoad.text = it.name
            binding.profileAvatarLoad.load(it.avatarUrl) {
                precision(Precision.EXACT)
                error(R.drawable.ic_github)
                scale(Scale.FILL)
            }
            binding.profileEmailLoad.text = it.email.toString()
            binding.profileCreationDateLoad.text = it.createdAt
        }
        viewModel.inProgress.observe(requireActivity()) { inProgress ->
            binding.loadingProcessLayout.isVisible = inProgress
            binding.rvProfileReposLoad.isEnabled = !inProgress
        }
    }

    override fun onPause() {
        super.onPause()
        requireActivity()
            .findViewById<Toolbar>(R.id.toolbar)
            .title = "User profile list"
    }
}