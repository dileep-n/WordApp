package com.example.wordapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentence_table")
data class Sentence(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "sentence") val sentence: String,
    @ColumnInfo(name = "translation") val translation: String,
    @ColumnInfo(name = "wordId") val wordId: Int,
    @ColumnInfo(name = "partsOfSpeech_id") val partsOfSpeech_id: Int,
    @ColumnInfo(name = "definitionId") val definitionId: Int,
    @ColumnInfo(name = "voiceType") val voiceType: Int, //Active Voice, Passive Voice
    @ColumnInfo(name = "tense") val tense: Int, //Present simple, Present continuous, Present perfect simple, Present perfect continuous,Past, Future
    @ColumnInfo(name = "sentenceType") val sentenceType: Int //Affirmative, Negative, Question, Interrogative
)
