package com.example.githubprofiles.data.db.room.dao

import androidx.room.*
import com.example.githubprofiles.data.db.entity.DBProfileCommon

@Dao
interface ProfileCommonDao {
    @Query("SELECT * FROM dbProfileCommon")
    fun getAllProfiles(): List<DBProfileCommon>
}