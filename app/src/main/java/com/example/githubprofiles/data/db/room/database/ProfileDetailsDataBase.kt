package com.example.githubprofiles.data.db.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubprofiles.data.db.entity.DBProfileDetails
import com.example.githubprofiles.data.db.room.dao.ProfileDetailsDao

@Database(entities = [DBProfileDetails::class], version = 1)
abstract class ProfileDetailsDataBase : RoomDatabase() {
    abstract fun profileDetailsDao(): ProfileDetailsDao
}