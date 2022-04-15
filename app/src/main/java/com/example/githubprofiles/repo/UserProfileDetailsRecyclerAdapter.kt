package com.example.githubprofiles.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.example.githubprofiles.R
import com.example.githubprofiles.repo.datasource.GitHubUserProfileDetailsDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileRepoListItemDTO

class UserProfileDetailsRecyclerAdapter(
    private val userProfileDetails: GitHubUserProfileDetailsDTO?,
    private val userProfileRepoList: List<GitHubUserProfileRepoListItemDTO>?
) : RecyclerView.Adapter<UserProfileDetailsRecyclerAdapter.UserProfileDetailsViewHolder>() {

    class UserProfileDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userProfileDetailsImage: ImageView = itemView.findViewById(R.id.user_profile_avatar_load)
        private val userProfileDetailsName: TextView = itemView.findViewById(R.id.user_profile_name_load)
        private val userProfileDetailsEmail: TextView = itemView.findViewById(R.id.user_profile_details_email_load)
        private val userProfileDetailsDate: TextView = itemView.findViewById(R.id.user_profile_details_creation_date)
        private val userProfileDetailsRepoName: TextView = itemView.findViewById(R.id.user_profile_repo_name_load)
        private val userProfileDetailsRepoDate: TextView = itemView.findViewById(R.id.user_repo_item_date_load)

        fun bind(
            userProfileDetailsData: GitHubUserProfileDetailsDTO?,
            userProfileDetailsRepoData: GitHubUserProfileRepoListItemDTO?
        ) {
            userProfileDetailsData?.apply {
                userProfileDetailsImage.load(avatarUrl) {
                    precision(Precision.EXACT)
                    error(R.drawable.ic_github)
                    scale(Scale.FILL)
                }
                userProfileDetailsName.text = name
                userProfileDetailsEmail.text = email.toString()
                userProfileDetailsDate.text = createdAt
            }

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
        holder.bind(userProfileDetails, userProfileRepoList?.get(position))
    }

    override fun getItemCount() = userProfileRepoList?.size ?: 0
}