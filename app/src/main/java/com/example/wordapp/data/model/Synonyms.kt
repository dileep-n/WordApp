package com.example.wordapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "synonyms_table")
data class Synonyms(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "synonyms") val synonyms: String,
    @ColumnInfo(name = "definitionId") val definitionId: Int
)
