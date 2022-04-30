package com.example.githubprofiles.ui.profilelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubprofiles.R
import com.example.githubprofiles.app
import com.example.githubprofiles.databinding.FragmentProfileListBinding
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import com.example.githubprofiles.ui.profilelist.viewmodel.ProfileListViewModel
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ProfileListFragment : Fragment(R.layout.fragment_profile_list) {

    private var _binding: FragmentProfileListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var profileListRepo: RepositoryUsecase.WebProfileCommonUsecase

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ProfileListViewModel

    private val controller by lazy { activity as Controller }

    private val adapter = ProfileListRecyclerAdapter()

    private var subscribe: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileListBinding.bind(view)

        viewModel = ViewModelProvider(
            viewModelStore,
            viewModelFactory
        )[
                ProfileListViewModel::class.java
        ]

        app.profileListDependenciesComponent.inject(this)

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
        subscribe = adapter.clickEvent
            .subscribe {
                controller.showProfileDetails(it)
            }
    }

    interface Controller {
        fun showProfileDetails(userLogin: String)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscribe?.dispose()
        _binding = null
    }

}