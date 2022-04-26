package com.example.githubprofiles.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBProfileCommon(
    @PrimaryKey
    val login: String,
    val avatarUrl: String
)
