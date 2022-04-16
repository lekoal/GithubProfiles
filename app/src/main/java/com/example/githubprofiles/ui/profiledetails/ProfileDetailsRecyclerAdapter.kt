package com.example.githubprofiles.ui.profiledetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprofiles.R
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO

class ProfileDetailsRecyclerAdapter(
    private val profileRepoList: List<GitHubProfileRepoListItemDTO>?
) : RecyclerView.Adapter<ProfileDetailsRecyclerAdapter.UserProfileDetailsViewHolder>() {

    class UserProfileDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userProfileDetailsRepoName: TextView =
            itemView.findViewById(R.id.profile_repo_name_load)
        private val userProfileDetailsRepoDate: TextView =
            itemView.findViewById(R.id.repo_creation_date_load)

        fun bind(
            profileDetailsRepoData: GitHubProfileRepoListItemDTO?
        ) {
            profileDetailsRepoData?.apply {
                userProfileDetailsRepoName.text = name
                userProfileDetailsRepoDate.text = createdAt
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserProfileDetailsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_profile_repo_item, parent, false)
        return UserProfileDetailsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserProfileDetailsViewHolder, position: Int) {
        holder.bind(profileRepoList?.get(position))
    }

    override fun getItemCount() = profileRepoList?.size ?: 0
}