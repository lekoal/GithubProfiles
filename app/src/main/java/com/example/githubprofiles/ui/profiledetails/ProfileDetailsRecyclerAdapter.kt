package com.example.githubprofiles.ui.profiledetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubprofiles.R
import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO

class ProfileDetailsRecyclerAdapter :
    RecyclerView.Adapter<ProfileDetailsRecyclerAdapter.ProfileDetailsViewHolder>() {

    private var reposData: List<GitHubProfileRepoListItemDTO> = emptyList()

    fun setData(repos: List<GitHubProfileRepoListItemDTO>) {
        reposData = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_profile_repo_item, parent, false)
        return ProfileDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileDetailsViewHolder, position: Int) {
        holder.bind(getRepoItem(position))
    }

    private fun getRepoItem(position: Int): GitHubProfileRepoListItemDTO = reposData[position]

    override fun getItemCount(): Int = reposData.size

    inner class ProfileDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repoName: TextView = itemView.findViewById(R.id.profile_repo_name_load)
        private val repoDate: TextView = itemView.findViewById(R.id.repo_creation_date_load)

        fun bind(repo: GitHubProfileRepoListItemDTO) {
            repoName.text = repo.name
            repoDate.text = repo.createdAt
        }
    }
}