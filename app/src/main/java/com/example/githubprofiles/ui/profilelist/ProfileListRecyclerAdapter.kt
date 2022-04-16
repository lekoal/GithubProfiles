package com.example.githubprofiles.ui.profilelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.example.githubprofiles.R
import com.example.githubprofiles.databinding.RvProfileItemBinding
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO

class ProfileListRecyclerAdapter :
    RecyclerView.Adapter<ProfileListRecyclerAdapter.ProfileListViewHolder>() {
    private var data: List<GitHubProfileListItemDTO> = emptyList()

    fun setData(profiles: List<GitHubProfileListItemDTO>) {
        data = profiles
        notifyItemRangeChanged(0, profiles.size)
    }

    class ProfileListViewHolder(private val binding: RvProfileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): ProfileListViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return ProfileListViewHolder(RvProfileItemBinding.inflate(inflater))
            }
        }

        fun bind(item: GitHubProfileListItemDTO) {
            binding.profileLoginLoad.text = item.login
            binding.profileAvatarMiniLoad.load(item.avatarUrl) {
                precision(Precision.EXACT)
                error(R.drawable.ic_github)
                scale(Scale.FILL)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileListViewHolder {
        return ProfileListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProfileListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): GitHubProfileListItemDTO = data[position]

    override fun getItemCount(): Int = data.size


}