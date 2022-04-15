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
import com.example.githubprofiles.repo.datasource.GitHubUserProfileListItemDTO

class UserProfileListRecyclerAdapter(
    private val userProfileList: List<GitHubUserProfileListItemDTO>?,
    private var onItemClickListener: OnItemClickListener?
) : RecyclerView.Adapter<UserProfileListRecyclerAdapter.UserProfileListViewHolder>() {

    class UserProfileListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userProfileImage: ImageView = itemView.findViewById(R.id.user_profile_avatar)
        private val userProfileLogin: TextView = itemView.findViewById(R.id.user_profile_name)

        fun bind(
            userProfileData: GitHubUserProfileListItemDTO?,
            onItemClickListener: OnItemClickListener?
        ) {
            userProfileData?.apply {
                userProfileLogin.text = login
                userProfileImage.load(avatarUrl) {
                    precision(Precision.EXACT)
                    error(R.drawable.ic_github)
                    scale(Scale.FILL)
                }
            }

            itemView.setOnClickListener {
                onItemClickListener?.onClick(userProfileData)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserProfileListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_user_profile_item, parent, false)
        return UserProfileListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserProfileListViewHolder, position: Int) {
        holder.bind(userProfileList?.get(position), onItemClickListener)
    }

    override fun getItemCount() = userProfileList?.size ?: 0

    class OnItemClickListener(
        val itemClickListener: (userProfileData: GitHubUserProfileListItemDTO?) -> Unit
    ) {
        fun onClick(userProfileData: GitHubUserProfileListItemDTO?) =
            itemClickListener(userProfileData)
    }
}