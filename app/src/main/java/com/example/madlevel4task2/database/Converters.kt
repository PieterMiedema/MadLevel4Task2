package com.example.madlevel4task2.database

import Moves
import Outcomes
import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun movesToString(move: Moves): String {
        return move.name.toString()
    }

    @TypeConverter
    fun stringToMoves(str: String): Moves {
        when(str) {
            "Rock" -> return Moves.Rock
            "Paper" -> return Moves.Paper
            "Scissors" -> return Moves.Scissors
        }
        return Moves.Rock
    }

    @TypeConverter
    fun outcomesToString(outcome: Outcomes): String {
        return outcome.name.toString()
    }

    @TypeConverter
    fun stringToOutcomes(str: String): Outcomes {
        when(str) {
            "Draw" -> return Outcomes.Draw
            "Player" -> return Outcomes.Player
            "Computer" -> return Outcomes.Computer
        }
        return Outcomes.Draw
    }


}