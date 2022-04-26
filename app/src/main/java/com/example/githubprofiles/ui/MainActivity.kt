package com.example.githubprofiles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubprofiles.databinding.ActivityMainBinding
import com.example.githubprofiles.ui.profiledetails.ProfileDetailsFragment
import com.example.githubprofiles.ui.profilelist.ProfileListFragment

class MainActivity : AppCompatActivity(), ProfileListFragment.Controller {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val profileListFragment: Fragment = ProfileListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(binding.listContainer.id, profileListFragment)
                .commit()
        }

    }

    override fun showProfileDetails(userLogin: String) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                binding.detailsContainer.id,
                ProfileDetailsFragment.newInstance(userLogin)
            )
            .commit()
    }
}