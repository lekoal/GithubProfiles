package com.example.githubprofiles.ui.profiledetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprofiles.R
import com.example.githubprofiles.data.web.WebRepoCommon

class ProfileDetailsRecyclerAdapter :
    RecyclerView.Adapter<ProfileDetailsRecyclerAdapter.ProfileDetailsViewHolder>() {

    private var reposData: List<WebRepoCommon> = emptyList()

    fun setData(repos: List<WebRepoCommon>) {
        reposData = repos
        notifyItemRangeChanged(0, reposData.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_profile_details_repo_item, parent, false)
        return ProfileDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileDetailsViewHolder, position: Int) {
        holder.bind(getRepoItem(position))
    }

    private fun getRepoItem(position: Int): WebRepoCommon = reposData[position]

    override fun getItemCount(): Int = reposData.size

    inner class ProfileDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repoName: TextView = itemView.findViewById(R.id.profile_details_repo_name_load)
        private val repoDate: TextView = itemView.findViewById(R.id.profile_details_repo_creation_date_load)

        fun bind(repo: WebRepoCommon) {
            repoName.text = repo.name
            repoDate.text = repo.createdAt
        }
    }
}