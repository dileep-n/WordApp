package com.example.wordapp.ui.main.Interface

import com.example.wordapp.data.model.Word
import java.text.FieldPosition

interface IWordActivity {
    fun onCellClickListener(data: Word)
    fun onCellEditListener(data: Word)
    fun onCellDeleteListener(data: Word)
}