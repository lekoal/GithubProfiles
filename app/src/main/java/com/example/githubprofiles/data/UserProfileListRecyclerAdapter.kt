package com.example.githubprofiles.data

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprofiles.R
import com.example.githubprofiles.repo.datasource.GitHubUserProfileListItemDTO

class UserProfileListRecyclerAdapter(
    private val userProfileList: List<GitHubUserProfileListItemDTO>,
    private var onItemClickListener: AdapterView.OnItemClickListener?
) : RecyclerView.Adapter<UserProfileListRecyclerAdapter.UserProfileListViewHolder>() {

    class UserProfileListViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        private val userProfileImage: ImageView = itemView.findViewById(R.id.user_profile_avatar)
        private val userProfileLogin: TextView = itemView.findViewById(R.id.user_profile_name)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserProfileListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}