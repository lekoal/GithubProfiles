package com.example.githubprofiles.ui.profiledetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO
import com.example.githubprofiles.utils.BasePresenter

const val PRESENTER_ID = "PRESENTER_ID"

class ProfileDetailsFragment : Fragment(R.layout.fragment_profile_details) {

    private var _binding: FragmentProfileDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: Presenter

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
        fun newInstance(userLogin: String): ProfileDetailsFragment {
            val fragment = ProfileDetailsFragment()
            fragment.userLogin = userLogin
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileDetailsBinding.bind(view)

        if (savedInstanceState == null) {
            val id = userLogin
            presenter = Presenter(id)
            app.presenterStore.savePresenter(presenter)
        } else {
            val presenterId = savedInstanceState.getString(PRESENTER_ID)!!
            presenter = app.presenterStore.getPresenter(presenterId) as Presenter
        }
        rvInit()
        getData(presenter.id)
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
            presenter.currentRepoList = it
            adapter.setData(it)
        }

        viewModel.profile.observe(requireActivity()) {
            presenter.currentProfileDetails = it
            binding.profileNameLoad.text = it.name
            binding.profileAvatarLoad.load(it.avatarUrl) {
                precision(Precision.EXACT)
                error(R.drawable.ic_github)
                scale(Scale.FILL)
            }
            binding.profileEmailLoad.text = it.email.toString()
            binding.profileCreationDateLoad.text = it.createdAt
        }

        viewModel.onError.observe(requireActivity()) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.inProgress.observe(requireActivity()) { inProgress ->
            binding.profileDetailsLoadingProcessLayout.isVisible = inProgress
            binding.rvProfileReposLoad.isEnabled = !inProgress
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(PRESENTER_ID, presenter.id)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

class Presenter(override val id: String) : BasePresenter {
    var currentProfileDetails: GitHubProfileDetailsDTO? = null
    var currentRepoList: List<GitHubProfileRepoListItemDTO>? = null
}