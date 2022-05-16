package com.example.githubprofiles.data.db.room.dao

import androidx.room.*
import com.example.githubprofiles.data.db.entity.DBProfileDetails

@Dao
interface ProfileDetailsDao {
    @Query("SELECT * FROM dbProfileDetails WHERE login LIKE :login")
    fun getProfileDetails(login:String): DBProfileDetails
}