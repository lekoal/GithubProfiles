package com.example.githubprofiles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubprofiles.R
import com.example.githubprofiles.ui.main.profilelist.UserProfileListFragment

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