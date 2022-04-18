package com.example.wordapp.data.dataSource.local.dao
import androidx.room.*
import com.example.wordapp.data.model.*
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Query("SELECT * FROM voiceType_table ORDER BY name ASC")
    fun getAlphabetizedVoiceType(): Flow<List<VoiceType>>

    @Query("SELECT * FROM tenses_table")
    fun getTense(): Flow<List<Tenses>>

    @Query("SELECT * FROM sentenceType_table ORDER BY name ASC")
    fun getAlphabetizedSentenceType(): Flow<List<SentenceType>>

//    @Query("SELECT * FROM meaning_table WHERE word_id = :id ORDER BY meaning ASC")
//    fun getAlphabetizedMeanings(id : Int): Flow<List<Meaning>>

//    @Insert
//    suspend fun insertMeaning(meaning: Meaning)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDefinition(definition: Definition)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPartsOfSpeech(partsOfSpeech: PartsOfSpeech)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSentence(sentence: Sentence)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVoiceType(voiceType: VoiceType)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTense(tense: Tenses)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSentenceType(sentenceType: SentenceType)

    @Query("SELECT * FROM partsOfSpeech_table ORDER BY name ASC")
    fun getPartsOfSpeech(): Flow<List<PartsOfSpeech>>

    @Query("SELECT * FROM definition_table WHERE wordId IN (:id) ORDER BY partsOfSpeech_id ASC")
    fun getAlphabetizedDefinitions(id: Int): Flow<List<Definition>>

    @Query("SELECT * FROM sentence_table WHERE definitionId IN (:id) ORDER BY sentence ASC")
    fun getAlphabetizedSentence(id: Int): Flow<List<Sentence>>




}