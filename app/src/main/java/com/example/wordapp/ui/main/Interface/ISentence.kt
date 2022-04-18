package com.example.wordapp.ui.main.Interface
import com.example.wordapp.data.model.Sentence

interface ISentence {
    fun onCellClickListener(sentence: Sentence)
    fun onCellEditListener(sentence: Sentence)
    fun onCellDeleteListener(sentence: Sentence)
}