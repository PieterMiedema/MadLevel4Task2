package com.example.madlevel4task2.dao

import androidx.room.*
import com.example.madlevel4task2.model.Match

@Dao
interface MatchDao {
    @Query("SELECT * FROM matchTable")
    suspend fun getAllMatches(): List<Match>

    @Insert
    suspend fun insertMatches(match: Match)

    @Query("DELETE FROM matchTable")
    suspend fun deleteMatches()

    @Update
    suspend fun updateMatches(match: Match)

    @Query("SELECT COUNT(draw) FROM matchTable WHERE draw = 1")
    suspend fun getDrawCount(): Int

    @Query("SELECT COUNT(win) FROM matchTable WHERE win = 1")
    suspend fun getWinCount(): Int

    @Query("SELECT COUNT(loose) FROM matchTable WHERE loose = 1")
    suspend fun getLooseCount(): Int
}