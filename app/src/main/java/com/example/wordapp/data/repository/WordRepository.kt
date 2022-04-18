@file:Suppress("SyntaxError", "SyntaxError", "SyntaxError", "SyntaxError", "SyntaxError",
    "SyntaxError", "SyntaxError"
)

package com.example.wordapp.data.repository

import androidx.annotation.WorkerThread
import com.example.wordapp.data.dataSource.local.dao.WordDao
import com.example.wordapp.data.model.*
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    val allSentenceType: Flow<List<SentenceType>> = wordDao.getAlphabetizedSentenceType()
    val allTense: Flow<List<Tenses>> = wordDao.getTense()
    val allVoiceType:  Flow<List<VoiceType>> = wordDao.getAlphabetizedVoiceType()
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()
    val allPartsOfSpeech:Flow<List<PartsOfSpeech>> = wordDao.getPartsOfSpeech()
   //val allVoiceType: List<VoiceType> = wordDao.getAlphabetizedVoiceType()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun allDefinition(id:Int): Flow<List<Definition>> = wordDao.getAlphabetizedDefinitions(id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun allSentence(id:Int): Flow<List<Sentence>> = wordDao.getAlphabetizedSentence(id)

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insertMeaning(meaning: Meaning) {
//        wordDao.insertMeaning(meaning)
//    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateWord(word: Word) {
        wordDao.updateWord(word)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDefinition(definition: Definition) {
        wordDao.insertDefinition(definition)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertSentence(sentence: Sentence) {
        wordDao.insertSentence(sentence)
    }



}