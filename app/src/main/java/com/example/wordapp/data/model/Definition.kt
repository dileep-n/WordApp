package com.example.wordapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "definition_table")
data class Definition(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "definition") val definition: String,
    @ColumnInfo(name = "translation") val translation: String,
    @ColumnInfo(name = "partsOfSpeech_id") val partsOfSpeech_id: Int, //Noun, Pronoun, Verb, Adverb, Adjective, Preposition, Conjunction, Interjection,  Others..
    @ColumnInfo(name = "wordId") val wordId: Int
)
