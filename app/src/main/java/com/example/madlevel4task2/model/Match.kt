package com.example.madlevel4task2.model

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
    var playerMove: Int,

    @ColumnInfo
    @NonNull
    var computerMove: Int,

    @ColumnInfo
    var timestamp: Date,

    @ColumnInfo
    @NonNull
    var result: Int,

    @ColumnInfo
    var draw: Boolean,

    @ColumnInfo
    var win: Boolean,

    @ColumnInfo
    var loose: Boolean,

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

): Parcelable