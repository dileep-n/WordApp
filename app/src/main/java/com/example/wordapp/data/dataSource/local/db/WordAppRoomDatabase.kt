package com.example.wordapp.data.dataSource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wordapp.data.dataSource.local.dao.WordDao
import com.example.wordapp.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(
    entities = [
        Word::class,
        PartsOfSpeech::class,
        Definition::class,
        VoiceType::class,
        Tenses::class,
        SentenceType::class,
        Sentence::class
               ],
    version = 5
)
abstract class WordAppRoomDatabase : RoomDatabase(){

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordAppRoomDatabase? = null
        private val DB_NAME = "word_database"



        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordAppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordAppRoomDatabase::class.java,
                    DB_NAME
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this code lab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()


                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(wordDao: WordDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

            wordDao.deleteAll()

            var word = Word(0, "Hello",)
            wordDao.insertWord(word)
            word = Word(0, "World!",)
            wordDao.insertWord(word)

            var partsOfSpeech = PartsOfSpeech(1,"Noun")
            wordDao.insertPartsOfSpeech(partsOfSpeech)
            partsOfSpeech = PartsOfSpeech(2,"Pronoun")
            wordDao.insertPartsOfSpeech(partsOfSpeech)
            partsOfSpeech = PartsOfSpeech(3,"Verb")
            wordDao.insertPartsOfSpeech(partsOfSpeech)
            partsOfSpeech = PartsOfSpeech(4,"Adverb")
            wordDao.insertPartsOfSpeech(partsOfSpeech)
            partsOfSpeech = PartsOfSpeech(5,"Adjective")
            wordDao.insertPartsOfSpeech(partsOfSpeech)
            partsOfSpeech = PartsOfSpeech(6,"Preposition")
            wordDao.insertPartsOfSpeech(partsOfSpeech)
            partsOfSpeech = PartsOfSpeech(7,"Conjunction")
            wordDao.insertPartsOfSpeech(partsOfSpeech)
            partsOfSpeech = PartsOfSpeech(8,"Interjection")
            wordDao.insertPartsOfSpeech(partsOfSpeech)


            var voiceType = VoiceType(1,"Active Voice")
            wordDao.insertVoiceType(voiceType)
            voiceType = VoiceType(2,"Passive Voice")
            wordDao.insertVoiceType(voiceType)

            var tense = Tenses(1,"Past simple",1,1)
            wordDao.insertTense(tense)
            tense = Tenses(2,"Past continuous",1,2)
            wordDao.insertTense(tense)
            tense = Tenses(3,"Past perfect simple",1,3)
            wordDao.insertTense(tense)
            tense = Tenses(4,"Past perfect continuous",1, 4)
            wordDao.insertTense(tense)

            tense = Tenses(5,"Present simple",2,1)
            wordDao.insertTense(tense)
            tense = Tenses(6,"Present continuous",2,2)
            wordDao.insertTense(tense)
            tense = Tenses(7,"Present perfect simple",2,3)
            wordDao.insertTense(tense)
            tense = Tenses(8,"Present perfect continuous",2,4)
            wordDao.insertTense(tense)

            tense = Tenses(9,"Future simple",3,1)
            wordDao.insertTense(tense)
            tense = Tenses(10,"Future continuous",3,2)
            wordDao.insertTense(tense)
            tense = Tenses(11,"Future perfect simple",3,3)
            wordDao.insertTense(tense)
            tense = Tenses(12,"Future perfect continuous",3,4)
            wordDao.insertTense(tense)

            var sentenceType = SentenceType(1,"Affirmative")
            wordDao.insertSentenceType(sentenceType)
            sentenceType = SentenceType(2,"Negative")
            wordDao.insertSentenceType(sentenceType)
            sentenceType = SentenceType(3,"Question")
            wordDao.insertSentenceType(sentenceType)
            sentenceType = SentenceType(4,"Interrogative")
            wordDao.insertSentenceType(sentenceType)


        }
    }



}