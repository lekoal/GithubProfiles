package com.example.githubprofiles.data.db.room.dao

import androidx.room.*
import com.example.githubprofiles.data.db.entity.DBRepoCommon

@Dao
interface RepoCommonDao {
    @Query("SELECT * FROM dbRepoCommon WHERE login LIKE :login")
    fun getRepoCommon(login: String): List<DBRepoCommon>
}