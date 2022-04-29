package com.example.githubprofiles.ui.profiledetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.example.githubprofiles.R
import com.example.githubprofiles.app
import com.example.githubprofiles.data.web.entity.WebProfileDetails
import com.example.githubprofiles.data.web.entity.WebRepoCommon
import com.example.githubprofiles.databinding.FragmentProfileDetailsBinding
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import com.example.githubprofiles.utils.BasePresenter
import com.example.githubprofiles.utils.PresenterStore
import javax.inject.Inject

const val PRESENTER_ID = "PRESENTER_ID"

class ProfileDetailsFragment : Fragment(R.layout.fragment_profile_details) {

    private var _binding: FragmentProfileDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var profileDetailsRepo: RepositoryUsecase.WebProfileDetailsUsecase

    @Inject
    lateinit var repoCommonRepo: RepositoryUsecase.WebRepoCommonUsecase

    @Inject
    lateinit var presenterStore: PresenterStore

    private lateinit var presenter: Presenter

    lateinit var userLogin: String

    private val viewModel: ProfileDetailsViewModel by viewModels {
        ProfileDetailsViewModelFactory(profileDetailsRepo, repoCommonRepo)
    }

    private val adapter = ProfileDetailsRecyclerAdapter()

    companion object {
        fun newInstance(userLogin: String): ProfileDetailsFragment {
            val fragment = ProfileDetailsFragment()
            fragment.arguments = Bundle()
            fragment.arguments?.putString(PRESENTER_ID, userLogin)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileDetailsBinding.bind(view)

        userLogin = this.arguments?.getString(PRESENTER_ID).toString()

        app.profileDetailsDependenciesComponent.inject(this)

        if (savedInstanceState == null) {
            presenter = Presenter(userLogin)
            app.presenterStore.savePresenter(presenter)
        } else {
            userLogin = this.arguments?.getString(PRESENTER_ID).toString()
            presenter = app.presenterStore.getPresenter(userLogin) as Presenter
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
        userLogin = this.arguments?.getString(PRESENTER_ID).toString()
        outState.putString(PRESENTER_ID, userLogin)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

class Presenter(override val id: String) : BasePresenter {
    var currentProfileDetails: WebProfileDetails? = null
    var currentRepoList: List<WebRepoCommon>? = null
}