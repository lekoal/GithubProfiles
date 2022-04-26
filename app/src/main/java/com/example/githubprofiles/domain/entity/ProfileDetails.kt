package com.example.githubprofiles.domain.entity

data class ProfileDetails(
    val login: String,
    val name: String,
    val createdAt: String,
    val email: String,
    val avatarUrl: String
)
