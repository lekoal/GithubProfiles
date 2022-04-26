package com.example.githubprofiles.data.db.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubprofiles.data.db.entity.DBProfileCommon
import com.example.githubprofiles.data.db.room.dao.ProfileCommonDao

@Database(entities = [DBProfileCommon::class], version = 1)
abstract class ProfileCommonDataBase : RoomDatabase() {
    abstract fun profileCommonDao(): ProfileCommonDao
}