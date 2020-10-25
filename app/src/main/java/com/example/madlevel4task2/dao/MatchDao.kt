package com.example.madlevel4task2.dao

import androidx.room.*
import com.example.madlevel4task2.model.Match

@Dao
interface MatchDao {
    @Query("SELECT * FROM matchTable")
    suspend fun getAllMatches(): List<Match>

    @Insert
    suspend fun insertMatches(match: Match)

    @Delete
    suspend fun deleteMatches(match: Match)

    @Update
    suspend fun updateMatches(match: Match)
}