package com.example.wordapp.ui.main.viewModel

import androidx.lifecycle.*
import com.example.wordapp.data.model.Word
import com.example.wordapp.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    //val test:Int = 0


    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()


    //val allMeanings: LiveData<List<Meaning>> = repository.allMeanings(test).asLiveData()
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
//    fun insert(word: Word) = viewModelScope.launch {
//        repository.insertWord(word)
//    }
    fun deleteWord(word: Word) = viewModelScope.launch {
        repository.deleteWord(word)
    }





}