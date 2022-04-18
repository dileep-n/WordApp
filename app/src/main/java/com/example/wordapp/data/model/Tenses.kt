package com.example.wordapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenses_table")
data class Tenses(
    @PrimaryKey(autoGenerate = false) var id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tense") val tense: Int,
    @ColumnInfo(name = "aspects") val aspects: Int
    )
