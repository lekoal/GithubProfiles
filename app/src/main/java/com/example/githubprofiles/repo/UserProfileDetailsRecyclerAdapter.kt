package com.example.githubprofiles.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprofiles.R
import com.example.githubprofiles.repo.datasource.GitHubUserProfileRepoListItemDTO

class UserProfileDetailsRecyclerAdapter(
    private val userProfileRepoList: List<GitHubUserProfileRepoListItemDTO>?
) : RecyclerView.Adapter<UserProfileDetailsRecyclerAdapter.UserProfileDetailsViewHolder>() {

    class UserProfileDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userProfileDetailsRepoName: TextView =
            itemView.findViewById(R.id.user_profile_repo_name_load)
        private val userProfileDetailsRepoDate: TextView =
            itemView.findViewById(R.id.user_repo_item_date_load)

        fun bind(
            userProfileDetailsRepoData: GitHubUserProfileRepoListItemDTO?
        ) {
            userProfileDetailsRepoData?.apply {
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
            .inflate(R.layout.rv_user_profile_repo_item, parent, false)
        return UserProfileDetailsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserProfileDetailsViewHolder, position: Int) {
        holder.bind(userProfileRepoList?.get(position))
    }

    override fun getItemCount() = userProfileRepoList?.size ?: 0
}