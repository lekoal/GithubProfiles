package com.example.githubprofiles.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBProfileDetails(
    @PrimaryKey
    val login: String,
    val name: String,
    val createdAt: String,
    val email: String,
    val avatarUrl: String
)
