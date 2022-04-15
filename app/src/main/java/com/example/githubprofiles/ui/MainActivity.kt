package com.example.githubprofiles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubprofiles.R
import com.example.githubprofiles.ui.profilelist.UserProfileListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserProfileListFragment.newInstance())
                .commitNow()
        }
    }
}