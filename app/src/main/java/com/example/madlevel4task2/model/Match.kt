package com.example.madlevel4task2.model

import Moves
import Outcomes
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "matchTable")
data class Match (
    @ColumnInfo
    @NonNull
    var playerMove: Moves,

    @ColumnInfo
    @NonNull
    var computerMove: Moves,

    @ColumnInfo
    var timestamp: Date,

    @ColumnInfo
    @NonNull
    var result: Outcomes,

    @ColumnInfo
    var win: Boolean,

    @ColumnInfo
    var loose: Boolean,

    @ColumnInfo
    var draw: Boolean,

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

): Parcelable