package com.example.githubprofiles.data.db.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubprofiles.data.db.entity.DBRepoCommon
import com.example.githubprofiles.data.db.room.dao.RepoCommonDao

@Database(entities = [DBRepoCommon::class], version = 1)
abstract class RepoCommonDataBase : RoomDatabase() {
    abstract fun repoCommonDao(): RepoCommonDao
}