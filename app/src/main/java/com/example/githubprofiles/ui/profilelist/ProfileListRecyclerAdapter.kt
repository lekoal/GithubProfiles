package com.example.githubprofiles.ui.profilelist

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
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class ProfileListRecyclerAdapter :
    RecyclerView.Adapter<ProfileListRecyclerAdapter.ProfileListViewHolder>() {
    private var data: List<GitHubProfileListItemDTO> = emptyList()

    private val clickSubject = PublishSubject.create<String>()

    val clickEvent: Observable<String> = clickSubject

    fun setData(profiles: List<GitHubProfileListItemDTO>) {
        data = profiles
        notifyItemRangeChanged(0, profiles.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_profile_item, parent, false)
        return ProfileListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): GitHubProfileListItemDTO = data[position]

    override fun getItemCount(): Int = data.size

    inner class ProfileListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(data[layoutPosition].login)
            }
        }

        private val profileLogin: TextView = itemView.findViewById(R.id.profile_login_load)
        private val profileAvatar: ImageView = itemView.findViewById(R.id.profile_avatar_mini_load)

        fun bind(item: GitHubProfileListItemDTO) {
            profileLogin.text = item.login
            profileAvatar.load(item.avatarUrl) {
                precision(Precision.EXACT)
                error(R.drawable.ic_github)
                scale(Scale.FILL)
            }
        }
    }
}