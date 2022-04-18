package com.example.wordapp.utils
import android.app.Application
import com.example.wordapp.data.dataSource.local.db.WordAppRoomDatabase
import com.example.wordapp.data.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication: Application()  {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WordAppRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { WordRepository(database.wordDao())}

}