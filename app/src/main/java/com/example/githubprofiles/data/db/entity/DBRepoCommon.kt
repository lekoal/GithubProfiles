package com.example.githubprofiles.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBRepoCommon(
    @PrimaryKey
    val login: String,
    val name: String,
    val createdAt: String
)