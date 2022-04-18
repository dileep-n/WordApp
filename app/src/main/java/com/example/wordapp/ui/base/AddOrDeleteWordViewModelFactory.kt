package com.example.wordapp.ui.base
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wordapp.data.model.Definition
import com.example.wordapp.data.model.Word
import com.example.wordapp.data.repository.WordRepository
import com.example.wordapp.ui.main.view.AddOrDeleteWordActivity
import com.example.wordapp.ui.main.viewModel.AddOrDeleteWordViewModel


class AddOrDeleteWordViewModelFactory(
    private val repository: WordRepository,
    private val word: Word,
    private val addOrDeleteWordActivity: AddOrDeleteWordActivity
): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddOrDeleteWordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddOrDeleteWordViewModel(repository,word,addOrDeleteWordActivity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}