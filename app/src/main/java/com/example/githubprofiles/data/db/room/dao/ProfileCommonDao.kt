package com.example.githubprofiles.data.db.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.githubprofiles.data.db.entity.DBProfileCommon

@Dao
interface ProfileCommonDao {
    @Query("SELECT * FROM dbProfileCommon")
    fun getAllProfiles(): List<DBProfileCommon>
}