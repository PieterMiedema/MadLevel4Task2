package com.example.madlevel4task2.repository

import android.content.Context
import com.example.madlevel4task2.dao.MatchDao
import com.example.madlevel4task2.database.MatchRoomDatabase
import com.example.madlevel4task2.model.Match

class MatchRepository(context: Context) {

    private var matchDao: MatchDao

    init {
        val matchRoomDatabase = MatchRoomDatabase.getDatabase(context)
        matchDao = matchRoomDatabase!!.matchDao()
    }

    suspend fun getAllMatches(): List<Match> {
        return matchDao.getAllMatches()
    }

    suspend fun insertMatches(match: Match) {
        matchDao.insertMatches(match)
    }

    suspend fun deleteMatches(match: Match) {
        matchDao.deleteMatches(match)
    }

    suspend fun updateMatches(match: Match) {
        matchDao.updateMatches(match)
    }
}